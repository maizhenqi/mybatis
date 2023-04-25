package com.example.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/temp")
public class TempController {

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){

        List<Map> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");map.put("name", "名字1"); map.put("pid", "0");list.add(map);map = new HashMap<>();
        map.put("id", "2");map.put("name", "名字2"); map.put("pid", "1");list.add(map);map = new HashMap<>();
        map.put("id", "3");map.put("name", "名字3"); map.put("pid", "2");list.add(map);map = new HashMap<>();
        map.put("id", "4");map.put("name", "名字4"); map.put("pid", "1");list.add(map);map = new HashMap<>();
        map.put("id", "5");map.put("name", "名字5"); map.put("pid", "0");list.add(map);map = new HashMap<>();
        map.put("id", "6");map.put("name", "名字6"); map.put("pid", "5");list.add(map);
        return change(list);
    }


    public static List change(List<Map> paramList){
        List returnList = new ArrayList();
        List<String> nextIdList = new ArrayList<>();
        List<Map> nextMapList = new ArrayList<>();
        Map<String, Integer> nextMapId = new HashMap<>();
        while (paramList.size() > 0){

            List<Integer> curAddList = new ArrayList<>();

            for (int index = 0; index < paramList.size(); index++){

                Map<String, String> map = paramList.get(index);
                if (map.get("pid").equals("0")){
                    curAddList.add(index);
                    continue;
                }
                if (nextMapId.get(map.get("pid")) != null){
                    curAddList.add(index);
                    Map curMap = nextMapList.get(nextMapId.get(map.get("pid")));
                    if (curMap != null){
                        if (curMap.get("child") != null){
                            List  childList = (List) curMap.get("child");
                            childList.add(paramList.get(index));
                        }else {
                            List  childList = new ArrayList();
                            childList.add(paramList.get(index));
                            curMap.put("child", childList);
                        }
                    }
                }

            }

            if (curAddList.size() > 0){

                boolean first = true;
                if (nextMapList.size() > 0){
                    first = false;
                }
                nextMapList = new ArrayList<>();
                nextMapId = new HashMap<>();
                for (int index = 0; index < curAddList.size(); index++){

                    if (first){
                        returnList.add(paramList.get(curAddList.get(index)));
                    }

                    nextMapList.add(paramList.get(curAddList.get(index)));

                    nextMapId.put((String) paramList.get(curAddList.get(index)).get("id"), nextMapList.size() - 1);

                }
                for (int index = 0; index < curAddList.size(); index++){
                    paramList.remove(nextMapList.get(index));
                }
            }

        }

        return returnList;

    }

}

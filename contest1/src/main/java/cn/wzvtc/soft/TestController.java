package cn.wzvtc.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://127.0.0.1:5500")
@RestController("/" )
public class TestController {
    @Autowired
    LvliRepository lvliRepository;

    @RequestMapping(value="/addlvli")
    public void addlvli(String lvli){
        Lvli lvli1=new Lvli("180",lvli);
        this.lvliRepository.save(lvli1);
    }
    @RequestMapping(value ="/lvlilist")
    public List<Lvli> getlvlist(){
        return this.lvliRepository.findAll();
    }
    @RequestMapping(value="userinfo")
    public Map<String,String> userinfo(HttpSession httpSession){
        Map<String,String> resultMap=new HashMap<>();
        if(httpSession.getAttribute("loginnumber")!=null){
            resultMap.put("myname","周阳阳");
            resultMap.put("mynumber","18002090241");
        }

        return resultMap;
    }

    @RequestMapping(value="login")
    public Map<String,String> login(@RequestBody Map<String,String> map, HttpSession httpSession){
        String password=map.get("password");
        String number=map.get("number");
        Map<String,String> resultMap=new HashMap<>();
        if("18002090241".equals(number) && "18002090241".equals(password)){
            httpSession.setAttribute("loginnumber",number);
            resultMap.put("result","success");
        }
        return resultMap;
    }
    @RequestMapping(value="logout")
    public void login(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("loginnumber");
    }
}
package demo.signup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String signup() {
        return "success";
    }
}

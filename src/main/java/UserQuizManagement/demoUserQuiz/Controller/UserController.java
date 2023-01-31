package UserQuizManagement.demoUserQuiz.Controller;

import UserQuizManagement.demoUserQuiz.CustomException;
import UserQuizManagement.demoUserQuiz.Entity.Users;
import UserQuizManagement.demoUserQuiz.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Users> getAllUser(){
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public Users getUserById(@PathVariable(value = "userId") Long user_id ) throws CustomException {
        return userService.getUserById(user_id);
    }

//    @PostMapping("/users/login")
//    public ResponseEntity<Users> loginUser(@RequestBody Users user) throws Exception {
//        Users loginUser = userService.loginUser(user);
//        return  ResponseEntity.ok(loginUser);
//    }

//    @PostMapping("/admin/login")
//    public ResponseEntity<Users> adminUser(@RequestBody Users user) throws Exception {
//        Users adminUser = userService.adminUser(user);
//        return  ResponseEntity.ok(adminUser);
//    }

    @PutMapping("/users/forgotpassword")
    public Users forgotPwd(@RequestBody Users users) throws CustomException {
        return  userService.forgotPassword(users);
    }


    @PostMapping("/createusers")
    public Users createUser(@RequestBody Users user) throws Exception {
        return userService.createNewUser(user);
    }
    @PostMapping("/createadmin")
    public Users createAdmin(@RequestBody Users user) throws Exception {
        return userService.createNewAdmin(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user ) throws CustomException {
        Users updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("users/{userId}")
    public Map<String,Boolean> deleteUsers(@PathVariable(value = "userId") Long userId) throws CustomException {
        return userService.deleteUser(userId);
    }

}

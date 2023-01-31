package UserQuizManagement.demoUserQuiz.Service;

import UserQuizManagement.demoUserQuiz.CustomException;
import UserQuizManagement.demoUserQuiz.Entity.Roles;
import UserQuizManagement.demoUserQuiz.Entity.Users;
import UserQuizManagement.demoUserQuiz.Repository.UserRepository;
import ch.qos.logback.core.util.COWArrayList;
import org.apache.tomcat.util.buf.CharChunk;
import org.apache.tomcat.util.net.openssl.ciphers.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Service
public class UserService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<Users> getUsers(){
        return userRepository.findAll();
    }
    public Users getUserById(Long userId) throws CustomException {
        Optional<Users> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new CustomException("ID not found");

        }
        return userOptional.get();
    }



//    public static String encodeToBase64(String message) {
//        return Base64.getEncoder().encodeToString(message.getBytes());
//    }

    public Users createNewUser(Users user) throws Exception {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles("USER");
        boolean optional= userRepository.existsByUserEmail(user.getUserEmail());
        if(optional){
            throw new CustomException("Email ID already exists !");
        }
        return userRepository.save(user);
    }

    public Users createNewAdmin(Users user) throws Exception {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setRoles("ADMIN");
        boolean optional= userRepository.existsByUserEmail(user.getUserEmail());
        if(optional){
            throw new CustomException("Email ID already exists !");
        }
        return userRepository.save(user);
    }

//    public Users loginUser(Users users) throws CustomException {
//        Optional<Users> usersOptional = userRepository.findByUserEmail(users.getUserEmail());
//        String password= users.getUserPassword();
//        String encodedPwd=encodeToBase64(password);
//        if ((usersOptional.get().getUserPassword()).equals(encodedPwd)){
//            return usersOptional.get();
//        }
//        else throw  new CustomException("Password does not match");
//    }

//    public Users adminUser(Users users) throws CustomException {
//        Optional<Users> usersOptional = userRepository.findByUserEmail(users.getUserEmail());
//        String password= users.getUserPassword();
//        String encodedPwd=encodeToBase64(password);
//        if (usersOptional.get().getRoles().equals(Roles.ADMIN))
//        {
//            if ((usersOptional.get().getUserPassword()).equals(encodedPwd)) {
//                System.out.println("Password Matched");
//            }
//            return usersOptional.get();
//        }
//        else throw  new CustomException("Password does not match");
//    }


    public Users forgotPassword(Users users) throws CustomException {
        Optional<Users> usersOptional = userRepository.findByUserEmail(users.getUserEmail());
        if (!usersOptional.isPresent()){
            throw new CustomException("User not registered");
        }
        String question = usersOptional.get().getQuestion();
        String answer = usersOptional.get().getAnswer();
        if (!answer.equals(users.getAnswer())
                || !usersOptional.get().getUserEmail().equals(users.getUserEmail())) {
            throw new CustomException("The security answer or email or security question does not matches");
        }
        usersOptional.get().setUserPassword(passwordEncoder.encode(users.getUserPassword()));

        Users users1= userRepository.save(usersOptional.get());
        return users1;
    }





    public Users updateUser(Users user) throws CustomException{
        Optional<Users> userOptional = userRepository.findById(user.getUserId());
        if(!userOptional.isPresent()){
            //throw new CustomException();
            throw new CustomException("No such id exists");
        }
//        Optional<User1> user1Optional = userRepository.findById(user1.getId());
        if(user.getUserName() != null) {
            userOptional.get().setUserName(user.getUserName());
        }
        if(user.getUserPhoneNo() != null) {
            userOptional.get().setUserPhoneNo(user.getUserPhoneNo());
        }
        if(user.getUserEmail() != null) {
            userOptional.get().setUserEmail(user.getUserEmail());
        }
        if (user.getDob()!=null){
            userOptional.get().setDob(user.getDob());
        }
        if (user.getQuestion()!=null){
            userOptional.get().setQuestion(user.getQuestion());
        }
        if (user.getAnswer()!=null){
            userOptional.get().setAnswer(user.getAnswer());
        }
        Users user1 = userRepository.save(userOptional.get());
        return user1;


    }
    public Map<String,Boolean> deleteUser(Long user_id) throws CustomException{
        Optional<Users> userOptional = userRepository.findById(user_id);
        if(!userOptional.isPresent()){
            throw new CustomException("User with this id not found");
        }
        userRepository.delete(userOptional.get());
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }

}


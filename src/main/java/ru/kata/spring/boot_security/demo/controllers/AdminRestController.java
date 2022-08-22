package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private final UserDetailsServiceImpl userService;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminRestController(UserDetailsServiceImpl userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> newUser( @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/users")
//    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
//        userService.save(user);
//        return ResponseEntity.ok(userService.getAllUsers());
//    }


//    @GetMapping("/roles")
//    public ResponseEntity<List<Role>> getRoles() {
//        return ResponseEntity.ok(roleServiceImpl.getAllRoles());
//    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<User> showUser(@PathVariable Integer id){
//        User user = userService.getById(id);
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/viewUser")
//    public ResponseEntity<User> showUser(Authentication auth) {
//        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
//    }
//



//    @GetMapping("/roles/{id}")
//    ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id){
//        return new ResponseEntity<>(roleServiceImpl.findById(id), HttpStatus.OK);
//    }

//    @PostMapping("/createNewUser")
//    public String createNewUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/admin";
//    }


//    @GetMapping()
//    public String showAllUsers(Principal principal, Model model) {
//        model.addAttribute("user_auth", userService.findByUsername(principal.getName()));
//        model.addAttribute("users", userService.getAllUsers());
//
//
//        List<Role> roleList = roleServiceImpl.getAllRoles();
//        model.addAttribute("user", new User());
//        model.addAttribute("list", roleList);
//        return "admin/admin";
//    }



//
//    @GetMapping("/roles")
//    public ResponseEntity<List<Role>> getRoles() {
//        return ResponseEntity.ok(roleServiceImpl.getAllRoles());
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable int id){
//        return ResponseEntity.ok(userService.getById(id));
//    }


//    @PatchMapping("/{id}/updateUser")
//    public String showUpdatedUser(@PathVariable("id") int id, User user) {
//        userService.update(user);
//        return "redirect:/admin";
//    }

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
}

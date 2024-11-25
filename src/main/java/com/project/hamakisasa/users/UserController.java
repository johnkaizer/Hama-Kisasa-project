package com.project.hamakisasa.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.prefs.Preferences;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // POST: Handle login
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes) {
        // Authenticate user
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            // Save the user ID, username, and role in preferences
            Preferences prefs = Preferences.userNodeForPackage(UserController.class);
            prefs.putLong("loggedInUserId", user.getId());
            prefs.put("loggedInUsername", username);
            prefs.put("loggedInUserRole", user.getRole());

            // Redirect based on user role
            if ("ADMIN".equals(user.getRole())) {
                return new ModelAndView(new RedirectView("/admin_dashboard"));
            } else if ("LANDLORD".equals(user.getRole())) {
                return new ModelAndView(new RedirectView("/landlord_dashboard"));
            } else if ("TENANT".equals(user.getRole())) {
                return new ModelAndView(new RedirectView("/user_dashboard"));
            } else {
                // Handle unknown roles if needed
                redirectAttributes.addFlashAttribute("errorMessage", "Access denied: Unknown role");
                return new ModelAndView(new RedirectView("/login"));
            }
        } else {
            // Invalid credentials; redirect to the login page with error
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
            return new ModelAndView(new RedirectView("/login"));
        }
    }

    // GET: Get current logged-in username
    @GetMapping("/currentUsername")
    public ResponseEntity<String> getCurrentUsername() {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        String loggedInUsername = prefs.get("loggedInUsername", null);
        if (loggedInUsername != null) {
            return new ResponseEntity<>(loggedInUsername, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET: Get current logged-in user details
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        String loggedInUsername = prefs.get("loggedInUsername", null);
        if (loggedInUsername != null) {
            User user = userService.getUserByUsername(loggedInUsername);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/currentUserId")
    public ResponseEntity<Long> getCurrentUserId() {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        Long loggedInUserId = prefs.getLong("loggedInUserId", -1L);
        if (loggedInUserId != -1L) {
            return new ResponseEntity<>(loggedInUserId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // POST: Handle logout
    @PostMapping("/logout")
    public ModelAndView logout() {
        // Clear stored preferences
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        prefs.remove("loggedInUserId");
        prefs.remove("loggedInUsername");
        prefs.remove("loggedInUserRole");

        // Redirect to login page
        return new ModelAndView(new RedirectView("/"));
    }
    // PUT: Update current logged-in user details
    @PutMapping("/current")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User updatedUser) {
        Preferences prefs = Preferences.userNodeForPackage(UserController.class);
        String loggedInUsername = prefs.get("loggedInUsername", null);

        if (loggedInUsername != null) {
            User currentUser = userService.getUserByUsername(loggedInUsername);
            if (currentUser != null) {
                currentUser.setName(updatedUser.getName());
                currentUser.setUsername(updatedUser.getUsername());
                currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
                currentUser.setPassword(updatedUser.getPassword()); // You may want to hash the password

                userService.save(currentUser); // Save the updated user
                return new ResponseEntity<>(currentUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/total")
    public long getTotalUsers() {
        return userService.getTotalUsers();
    }


}


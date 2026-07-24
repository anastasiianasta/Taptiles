package sk.tuke.gamestudio.server.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sk.tuke.gamestudio.server.dto.RegisterUserDto;
import sk.tuke.gamestudio.server.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (isAuthenticated(authentication)) {
            return "redirect:/taptiles";
        }
        return "taptiles-login";
    }

    @GetMapping("/register")
    public String register(Model model, Authentication authentication) {
        if (isAuthenticated(authentication)) {
            return "redirect:/taptiles";
        }
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "taptiles-register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUserDto") RegisterUserDto registerUserDto,
                           BindingResult bindingResult) {
        validateUniqueUser(registerUserDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "taptiles-register";
        }

        userService.register(registerUserDto);
        return "redirect:/login?registered";
    }

    private void validateUniqueUser(RegisterUserDto dto, BindingResult bindingResult) {
        if (dto.getPassword() != null && !dto.getPassword().equals(dto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "passwords.notMatch", "ACCESS KEYS DO NOT MATCH");
        }
        if (dto.getUsername() != null && userService.usernameExists(dto.getUsername())) {
            bindingResult.rejectValue("username", "username.exists", "USERNAME ALREADY EXISTS");
        }
        if (dto.getEmail() != null && userService.emailExists(dto.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "EMAIL CHANNEL ALREADY EXISTS");
        }
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
}

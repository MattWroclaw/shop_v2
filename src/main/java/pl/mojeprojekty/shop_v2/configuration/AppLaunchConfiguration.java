package pl.mojeprojekty.shop_v2.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import pl.mojeprojekty.shop_v2.entity.Role;
import pl.mojeprojekty.shop_v2.repositories.RoleRepository;

@Configuration
@RequiredArgsConstructor
public class AppLaunchConfiguration {

    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadRoles() {
        pl.mojeprojekty.shop_v2.entity.Role admin = roleRepository.findByRole("ADMIN");
        pl.mojeprojekty.shop_v2.entity.Role user = roleRepository.findByRole("USER");

        if (admin == null) {
            admin = new Role(1L, "ADMIN");
            roleRepository.save(admin);
        }

        if (user == null) {
            user = new Role(2L, "USER");
            roleRepository.save(user);
        }
    }
}

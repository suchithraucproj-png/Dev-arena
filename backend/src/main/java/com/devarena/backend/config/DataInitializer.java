package com.devarena.backend.config;

import com.devarena.backend.model.CourseOffer;
import com.devarena.backend.model.Role;
import com.devarena.backend.model.User;
import com.devarena.backend.repository.CourseOfferRepository;
import com.devarena.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseOfferRepository courseOfferRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           CourseOfferRepository courseOfferRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.courseOfferRepository = courseOfferRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@devarena.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setDisplayName("Admin");
            admin.setBio("Platform administrator");
            userRepository.save(admin);

            // Seed course offers
            if (courseOfferRepository.count() == 0) {
                CourseOffer o1 = new CourseOffer();
                o1.setTitle("Python for Data Science Certification");
                o1.setDescription("Complete Python for Data Science course with hands-on projects and official certification.");
                o1.setCreditsRequired(50);
                o1.setLink("#");
                courseOfferRepository.save(o1);

                CourseOffer o2 = new CourseOffer();
                o2.setTitle("Full-Stack Web Development Bootcamp");
                o2.setDescription("Master React, Node.js, and MongoDB in this comprehensive bootcamp with certificate.");
                o2.setCreditsRequired(100);
                o2.setLink("#");
                courseOfferRepository.save(o2);

                CourseOffer o3 = new CourseOffer();
                o3.setTitle("AWS Cloud Architect Certification");
                o3.setDescription("Prepare for the AWS Solutions Architect exam with labs, quizzes, and a practice exam.");
                o3.setCreditsRequired(200);
                o3.setLink("#");
                courseOfferRepository.save(o3);

                CourseOffer o4 = new CourseOffer();
                o4.setTitle("Machine Learning Specialization");
                o4.setDescription("Deep dive into ML algorithms, neural networks, and model deployment with certification.");
                o4.setCreditsRequired(150);
                o4.setLink("#");
                courseOfferRepository.save(o4);
            }

            System.out.println("Default admin and course offers created.");
        }
    }
}

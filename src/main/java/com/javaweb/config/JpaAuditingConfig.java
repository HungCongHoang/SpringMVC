package com.javaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider"): Kích hoạt tính năng JPA Auditing trong Spring Data JPA. 
//Nó tự động theo dõi và ghi lại các trường như createdBy, modifiedBy. 
//auditorAwareRef = "auditorProvider" chỉ ra rằng Spring sẽ dùng bean auditorProvider để lấy thông tin về người thực hiện thao tác.
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	public static class AuditorAwareImpl implements AuditorAware<String> {

		@Override
		public String getCurrentAuditor() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null) {
				return null;
			}
			return authentication.getName();
		}
	}
}

package com.Master_Dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Master_Dashboard.Encryption.Encryption;

@SpringBootApplication
public class MasterDashboardApplication {
	
	public static void main(String[] args) {
		
		System.out.println(Encryption.decString("/bXkO7n8oMLWaSXFM7VbP1FgYvQGBBun63zzPsC5p1gDFImoJ1gKF0ewm0+uQDTPJ+I6B6AIK7d+qCsF/p1yKSydw2IYNwAMaYWXToG0y4WE5j7jLu18gLrHMfYABiFpr/gRgGHpJOpftb1HaaVrAMgq0NEv3wGqfFnoY6owrcKuisi9GqiQ1s9NyP4mu2Jbk50TzHf86aHaX+pCSOzf0V3MF2CD30laEVLsnKxQi1egz+cGjbAxZddpc3MWbqUQH2Jldl9jV7//L092jjc54MGw8lotmnjiTvPmsfytyU4I/ttqbT+nqEFKRRu/4RtzAIF8XXOBa/cIS9X6V/9xungD3jcssjk+8mt4es7u4/dFce1ALexQLH6HVf9Uziy8aF1uK7BvOyEAPTuAWbbhuIl4JdKS+TF9c9aHtOV3fycS3kNHYF3xK4/vMdz8nS05j3vWbEdN1ZS4rBOy5gMV9ILf34jlvEVioylIEdXuX+Ri8JONmNASNvCuz4MxkcDIjlfdWmsr58rZzoBh6QGNnaeQ4J5xmBwlAJwZ96hqCOL4o0Q+WtaYdSo1851r8l3SdPZAMin5as/vG+sfCYjhu7BrRlSPlxXNBQ5CmRVgHyxadwokP3uj4nAL2tXcpEn2HS6eYzPdIt4DUkEwEOfU80H356q86rEuwPxBmJ2a0cyF01NFoY3Ud0ce0gikhqMfE4QH6ykRwRClRA602v+eF54fkLTSDLMl0mRYtnGwBKM="));
		
		SpringApplication.run(MasterDashboardApplication.class, args);
		
	}
}

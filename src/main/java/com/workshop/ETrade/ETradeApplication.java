package com.workshop.ETrade;

import com.workshop.ETrade.Domain.Facade;
import com.workshop.ETrade.Repository.*;
import com.workshop.ETrade.Service.InitExecuter.LoadServiceFromInitState;
import com.workshop.ETrade.Service.SystemService;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.workshop.ETrade.Repository")
public class ETradeApplication implements CommandLineRunner {
	private boolean initialize;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private StoreBasketRepository storeBasketRepository;

	@Autowired
	private SystemManagerRepository systemManagerRepository;

	@Autowired
	private BidRepository bidRepository;

	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private DiscountRepository discountRepository;
	@Autowired
	private TrafficRepository trafficRepository;
@Autowired
	SystemService service;
	public static void main(String[] args) {
		SpringApplication.run(ETradeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (!initialize) {
			AllRepos.setStoreRepo(storeRepository);
			AllRepos.setProductRepo(productRepository);
			AllRepos.setMemberRepo(memberRepository);
			AllRepos.setStoreBasketRepo(storeBasketRepository);
			AllRepos.setSystemManagerRepo(systemManagerRepository);
			AllRepos.setBidRepo(bidRepository);
			AllRepos.setPolicyRepo(policyRepository);
			AllRepos.setDiscountRepo(discountRepository);
			AllRepos.setTrafficRepo(trafficRepository);
			initialize = true;
		}
		try {
			LoadServiceFromInitState.loadConfig(args[0]);
			this.service.initFacade();
			File file = new File("src\\main\\java\\com\\workshop\\ETrade\\Service\\InitExecuter\\initState.json");
			String path = file.getAbsolutePath();

			LoadServiceFromInitState.loadFromFile(path, service);
			this.service.allLogOut();
		}catch (Exception e){
			System.out.println(e);
		}
	}
//
//	@Bean
//	public ServletWebServerFactory servletContainer() {
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//		tomcat.addAdditionalTomcatConnectors(redirectConnector());
//		return tomcat;
//	}
//
//	private Connector redirectConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(8080);
//		connector.setSecure(false);
//		connector.setRedirectPort(8443);
//		return connector;
//	}

}

# spring-boot-async-example with custom-pool

As Maven is used as the build system, run:

    ./mvn clean install

To run the project, just run one of the following commands:

    java -jar target/async-service.jar

    ./mvn spring-boot:run


## Custom Pool

    @Bean(name = "threadPoolAsync")
	public Executor executor1() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("AsyncThread::");
		executor.initialize();
		return executor;
	}

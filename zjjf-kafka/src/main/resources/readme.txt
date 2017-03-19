#集成步骤：
1、引用该工程或者jar包
2、编写Mykafka.properties（参考用例在conf目录下kafka.properties，或者直接使用该文件不用自己配置编写）
3、在spring配置文件做一下配置,需要自己配置的加载Mykafka.properties，否则加载kafka.properties
	<!-- 加载配置信息 -->
	<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath*:config/dao/jdbc.properties</value>
				<!--<value>classpath*:config/kafka.properties</value>-->
				<value>classpath*:config/Mykafka.properties</value>
			</list>
		</property>
	</bean>
4、如果需要使用消费者，参照ZjjfKafkaListener类编写自己的kafka消费者，并且实例化。
5、如果需要使用生产者，使用下面的语句语句引用：
	@Autowired
	ZjjfKafkaProducer zjjfKafkaProducer;
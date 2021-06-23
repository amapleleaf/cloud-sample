package com.sample.cloud.apiclient;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiClientScanRegistrar  implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(ApiClientScan.class.getName());
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ApiClientScan.class.getName()));
        scanner.addIncludeFilter(new AnnotationTypeFilter(ApiClient.class));

        Set<String> basePackages = getBasePackages(mapperScanAttrs,importingClassMetadata);
        for (String scanPackage : basePackages) {
            for (BeanDefinition candidateComponent : scanner.findCandidateComponents(scanPackage)) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // verify annotated class is an interface
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    Assert.isTrue(annotationMetadata.isInterface(),
                            "@ApiClient can only be specified on an interface");

                    Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(ApiClient.class.getCanonicalName());
                    // 获取RestClient注解值
                    try {
                        // 动态定义bean
                        registerClientConfiguration(registry,annotationMetadata,attributes);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void registerClientConfiguration(BeanDefinitionRegistry registry, AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        try {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder .genericBeanDefinition(ApiClientFactoryBean.class);
            //builder.addConstructorArgValue(Class.forName(annotationMetadata.getClassName()));
            builder.addPropertyValue("target",Class.forName(annotationMetadata.getClassName()));
            //String alias =  getClientName(attributes) + "ApiClient";
            String alias =  getClientName(attributes);
            /*String qualifier = getQualifier(attributes);
            if (StringUtils.hasText(qualifier)) {
                alias = qualifier;
            }*/
            //builder.addConstructorArgValue(alias);
            builder.addPropertyValue("name",alias);
            String invokerClassStr = environment.getProperty("apiclient.invoker."+alias);
            if(!StringUtils.hasText(invokerClassStr)){
                throw new RuntimeException("未找到apiclient:"+alias+"对应的invoker配置类！");
            }
            builder.addPropertyValue("apiClientInvoker",Class.forName(invokerClassStr));
            BeanDefinitionHolder holder = new BeanDefinitionHolder(builder.getBeanDefinition(), annotationMetadata.getClassName(),new String[] { alias });
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
            //registry.registerBeanDefinition("ApiClient-" + getClientName(attributes),builder.getBeanDefinition());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String getQualifier(Map<String, Object> client) {
        if (client == null) {
            return null;
        }
        String qualifier = (String) client.get("qualifier");
        if (StringUtils.hasText(qualifier)) {
            return qualifier;
        }
        return null;
    }
    private String getClientName(Map<String, Object> client) {
        if (client == null) {
            return null;
        }
        String value = (String) client.get("value");
        if (!StringUtils.hasText(value)) {
            value = (String) client.get("name");
            return value;
        }
        if (StringUtils.hasText(value)) {
            return value;
        }
        throw new IllegalStateException("Either 'name' or 'value' must be provided in @"
                + ApiClient.class.getSimpleName());
    }
    protected Set<String> getBasePackages(AnnotationAttributes annoAttrs,AnnotationMetadata importingClassMetadata){
        Set<String> basePackages =new LinkedHashSet<>();
        basePackages.addAll(
                Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));

        basePackages.addAll(
                Arrays.stream(annoAttrs.getStringArray("basePackages")).filter(StringUtils::hasText).collect(Collectors.toList()));
        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }
    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}

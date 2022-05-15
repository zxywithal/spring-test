package org.think.in.spring.annotation.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 偶数 Profile 条件
 * @see Condition
 */
public class EvenProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //条件上下文
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles(Profiles.of("even"));
    }
}

package login;

import enums.Folder;
import enums.Method;
import enums.Role;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;

import java.util.*;
import java.util.stream.Collectors;

public final class SecurityHandlerBuilder {

    private final ConstraintSecurityHandler security = new ConstraintSecurityHandler();

    public ConstraintSecurityHandler build(LoginService loginService) {
        security.setLoginService(loginService);

        final List<ConstraintMapping> constraintMappings = new ArrayList<>();

        constraintMappings.addAll(constraintGetMapping(
                buildConstraint(Role.MANAGER),
                Collections.singletonList(Folder.MANAGER)
        ));

        constraintMappings.addAll(constraintPostMapping(
                buildConstraint(Role.MANAGER),
                new LinkedList<>(){{add(Folder.MANAGER); add(Folder.MANAGER_DELETE);}}
        ));

        constraintMappings.addAll(constraintGetMapping(
                buildConstraint(Role.MANAGER, Role.GUEST),
                Collections.singletonList(Folder.GUEST)
        ));

        security.setConstraintMappings(constraintMappings);
        security.setAuthenticator(new BasicAuthenticator());
        security.setDenyUncoveredHttpMethods(true);
        return security;
    }

    private static Constraint buildConstraint(Role... userRoles) {
        String[] roles = Arrays.stream(userRoles).map(userRole -> userRole.value).toArray(String[]::new);
        return buildConstraint(roles);
    }

    private static Constraint buildConstraint(String... userRoles) {
        final Constraint starterConstraint = new Constraint();
        starterConstraint.setName(Constraint.__BASIC_AUTH);
        starterConstraint.setRoles(userRoles);
        starterConstraint.setAuthenticate(true);
        return starterConstraint;
    }

    private static Collection<ConstraintMapping> constraintGetMapping(Constraint constraint,
                                                                      Collection<Folder> paths) {
        return constraintMapping(constraint, paths, Method.GET);
    }

    private static Collection<ConstraintMapping> constraintPostMapping(Constraint constraint,
                                                                      Collection<Folder> paths) {
        return constraintMapping(constraint, paths, Method.POST);
    }

    private static Collection<ConstraintMapping> constraintMapping(Constraint constraint,
                                                                   Collection<Folder> paths,
                                                                   Method method) {
        return paths.stream()
                .map(path -> {
                            final ConstraintMapping mapping = new ConstraintMapping();
                            mapping.setConstraint(constraint);
                            mapping.setPathSpec(path.value);
                            mapping.setMethod(method.value);
                            return mapping;
                        }
                ).collect(Collectors.toList());
    }
}
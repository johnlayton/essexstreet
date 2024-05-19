package org.essexstreet;

import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.assertions.Template;
import java.io.File;
import java.util.Collections;

class AppStackTest {

    @Test
    void testAppStack() {
        if (new File(AppStack_Old.functionPath()).exists()) {
            AppStack_Old stack = new AppStack_Old(new App(), "TestMicronautAppStack");
            Template template = Template.fromStack(stack);
            template.hasResourceProperties("AWS::Lambda::Function", Collections.singletonMap("Handler", "org.essexstreet.FunctionRequestHandler"));
        }
    }
}

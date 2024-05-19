package org.essexstreet;

import software.amazon.awscdk.App;

public class Main {
    public static void main(final String[] args) {
        App app = new App();
        new AppStack_Old(app, "MicronautAppStack");
        app.synth();
    }
}

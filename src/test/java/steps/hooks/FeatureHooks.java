package steps.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by silvia valencia on 4/15/2015.
 */
public class FeatureHooks {
    private static String lastFeature;
    private static String currentFeature;
    private static Map<String, IHookMethod> featuresHooksMap = new HashMap<String, IHookMethod>();
    final static Logger logger = Logger.getLogger(FeatureHooks.class);
    private static boolean featureFlag = false;

    //****************************************************************
    //Hooks for any scenario
    //****************************************************************
    @Before(order=4)
    public void beforeFeature(Scenario scenario) {
        logger.info("Start scenario: " + scenario.getName());

        //To run the after hook methods in the last scenario of the features
        currentFeature = scenario.getId().split(";")[0];                          //get the feature name
        if (!currentFeature.equals(lastFeature)) {
            executeAfterHookMethod();
            lastFeature = currentFeature;
            featureFlag = false;
        }
    }

    public static void executeAfterHookMethod()  {

        //Run the after hook method if the (feature, after hook method) was added to the map
        if (featuresHooksMap.get(lastFeature) != null) {
            featuresHooksMap.get(lastFeature).executeMethod();
        }
    }

    //****************************************************************
    //Hooks for @Resources feature
    //****************************************************************
    @Before("@resource")
    public void beforeLocationsFeature() {
        featuresHooksMap.put("resource", () -> afterResourceFeature());       //Check the name of the feature obtained to use the same value as key
        if(!featureFlag){
            ResourcesSetup.beforeResourceFeature();
            featureFlag = true;
        }
    }

    public void afterResourceFeature() {
        ResourcesSetup.afterResourceFeature();
    }
}
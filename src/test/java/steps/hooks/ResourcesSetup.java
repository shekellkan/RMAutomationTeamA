package steps.hooks;

import api.APIResourcesMethods;
import entities.ResourceEntity;

import java.util.ArrayList;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/15/15
 * Time: 5:02 PM
 */
public class ResourcesSetup {
    static ArrayList<ResourceEntity> listOfResources = new ArrayList<ResourceEntity>();
    static APIResourcesMethods apiResourcesMethods = new APIResourcesMethods();
    public static void beforeResourceFeature() {
        System.out.println("LOOK MOM IM IN THE BEFORE FEATURE");


        ResourceEntity resource1 = new ResourceEntity();
        resource1.setAllFields("Mac_Pro","Mac pro computer","My mac pro computer","fa-desktop");
        ResourceEntity resource2 = new ResourceEntity();
        resource2.setAllFields("Telephone_Pro","Inter Communicator","My inter communicator telephone","fa-group");
        ResourceEntity resource3 = new ResourceEntity();
        resource3.setAllFields("04ac5_Pro","Mac computer","My mac computer","fa-desktop");

        listOfResources.add(resource1);
        listOfResources.add(resource2);
        listOfResources.add(resource3);

        for(ResourceEntity item : listOfResources){
            apiResourcesMethods.createResource(item);
        }

    }

    public static void afterResourceFeature() {
        System.out.println("LOOK MOM IM IN THE AFTER FEATURE");

        for(ResourceEntity item : listOfResources){
            apiResourcesMethods.removeResource(item);
        }
    }
}

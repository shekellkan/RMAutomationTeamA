package Models;

/**
 * User: jeancarlorodriguez
 * Date: 12/8/15
 * Time: 5:25 PM
 */
public class ResourceModel {

    private String name;
    private String displayName;
    private String description;
    private String iconName;

    public void setName(String name){ this.name = name; }
    public String getName(){return name;}

    public void setDisplayName(String displayName){ this.displayName = displayName; }
    public String getDisplayName(){return displayName;}

    public void setDescription(String description){ this.description = description; }
    public String getDescription(){return description;}

    public void setIconName(String iconName){ this.iconName = iconName; }
    public String getIconName(){return iconName;}

    public void fillAllFields(String name, String displayName, String description, String iconName)
    {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.iconName = iconName;
    }

}

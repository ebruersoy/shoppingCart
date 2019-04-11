package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ebru Göksal
 */

public class Category {
    private String title;
    private Category parentCategory;
    private List<Campaign> campaignList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }
}

package nativeapp.android;

import com.epam.jdi.light.mobile.elements.common.app.android.SearchViewButton;
import com.epam.jdi.light.mobile.elements.common.app.android.SearchViewField;
import com.epam.jdi.light.mobile.elements.pageobjects.annotations.JApp;
import com.epam.jdi.light.mobile.elements.pageobjects.annotations.MobileFindBy;

@JApp(appPackage = "", appActivity = "")
public class SettingsApp {

    @MobileFindBy(id = "com.android.settings:id/search_action_bar")
    public static SearchViewButton search;

    @MobileFindBy(id = "android:id/search_src_text")
    public static SearchViewField searchText;

}

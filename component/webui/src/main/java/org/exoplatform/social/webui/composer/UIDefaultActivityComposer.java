package org.exoplatform.social.webui.composer;

import org.exoplatform.social.core.activity.model.Activity;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.identity.provider.SpaceIdentityProvider;
import org.exoplatform.social.core.manager.ActivityManager;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.exoplatform.social.webui.activity.UIDefaultActivity;
import org.exoplatform.social.webui.composer.UIComposer.PostContext;
import org.exoplatform.social.webui.space.UISpaceActivitiesDisplay;
import org.exoplatform.web.application.ApplicationMessage;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIApplication;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;

/**
 * Created by IntelliJ IDEA.
 * User: zun
 * Date: Jun 21, 2010
 * Time: 2:15:07 PM
 * To change this template use File | Settings | File Templates.
 */
@ComponentConfig()
public class UIDefaultActivityComposer extends UIActivityComposer {

  public UIDefaultActivityComposer() {
    setReadyForPostingActivity(true);
  }

  @Override
  public void onPostActivity(PostContext postContext, UIComponent source, WebuiRequestContext requestContext, String postedMessage) throws Exception {
    if(postContext == UIComposer.PostContext.SPACE){
      UIApplication uiApplication = requestContext.getUIApplication();
      if (postedMessage.equals("")) {
        uiApplication.addMessage(new ApplicationMessage("UIComposer.msg.error.Empty_Message",
                                                      null,
                                                      ApplicationMessage.ERROR));
      } else {
        String member = requestContext.getRemoteUser();
        final UIComposer uiComposer = (UIComposer) source;
        UISpaceActivitiesDisplay uiDisplaySpaceActivities = (UISpaceActivitiesDisplay) getActivityDisplay();
        Space space = uiDisplaySpaceActivities.getSpace();

        ActivityManager activityManager = uiComposer.getApplicationComponent(ActivityManager.class);
        IdentityManager identityManager = uiComposer.getApplicationComponent(IdentityManager.class);
        Identity spaceIdentity = identityManager.getOrCreateIdentity(SpaceIdentityProvider.NAME,
                                                                 space.getId(),
                                                                 false);
        Identity userIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, member);
        Activity activity = new Activity(userIdentity.getId(),
                                     SpaceService.SPACES_APP_ID,
                                     postedMessage,
                                     null);
        activity.setType(UIDefaultActivity.ACTIVITY_TYPE);
        activityManager.saveActivity(spaceIdentity, activity);
      }
    } else if(postContext == PostContext.PEOPLE){
      
    }
  }

  @Override
  protected void onClose(Event<UIActivityComposer> event) {
  }

  @Override
  protected void onSubmit(Event<UIActivityComposer> event) {
  }

  @Override
  protected void onActivate(Event<UIActivityComposer> event) {
  }
}
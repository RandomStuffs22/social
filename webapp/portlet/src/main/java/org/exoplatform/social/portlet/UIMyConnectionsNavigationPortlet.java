/*
 * Copyright (C) 2003-2007 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.portlet;

import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.web.controller.QualifiedName;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * Manages the navigation of connections.<br>
 *   - Decides which node is current selected.<br>
 *   - Gets the current viewer name.<br>
 */

@ComponentConfig(
  lifecycle = UIApplicationLifecycle.class,
  template = "app:/groovy/social/portlet/UIMyConnectionsNavigationPortlet.gtmpl"
)
public class UIMyConnectionsNavigationPortlet extends UIPortletApplication {

  /**
   * Default Constructor.<br>
   * @throws Exception
   */
  public UIMyConnectionsNavigationPortlet() throws Exception { }

  /**
   * Returns the current selected node.<br>
   *
   * @return selected node.
   */
  public String getSelectedNode() {
    PortalRequestContext pcontext = Util.getPortalRequestContext();
    String path = pcontext.getControllerContext().getParameter(QualifiedName.parse("gtn:path"));
    return path.split("/")[1];
  }
}

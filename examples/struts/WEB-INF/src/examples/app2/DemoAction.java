/*
 * Copyright 2003-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples.app2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * <p>A simple action used to demonstrate the view tools.</p>
 *
 * @author <a href="mailto:sidler@teamup.com"/>Gabe Sidler</a>
 * @version $Id: DemoAction.java,v 1.5 2004/03/12 19:41:08 marino Exp $
 */
public class DemoAction extends Action
{

	/**
	 * Handle server requests.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
	 */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                 throws IOException, ServletException
    {
        String action;
	    HttpSession session;

        // Create serveral error messages to demontrate the output in a template
        ActionMessages errors = new ActionMessages();

        // Add some global errors
        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error01"));
        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error02"));

        // Add some specific errors
        errors.add("language", new ActionMessage("error10"));
        errors.add("language", new ActionMessage("error11"));

        // Save error messages to request attributes
        saveErrors(request, errors);


        // Create serveral error messages to demontrate the output in a template
        ActionMessages messages = new ActionMessages();

        // Add some global messages
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message01"));
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message02"));

        // Add some specific messages
        messages.add("foobar", new ActionMessage("message10"));
        messages.add("foobar", new ActionMessage("message11"));

        // Save messages to request attributes
        saveMessages(request, messages);


        // Create and save a new transaction token
        saveToken(request);

        return (mapping.findForward("home"));

    }
}


/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.velocity.tools.struts;


import java.util.Locale;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.upload.MultipartRequestWrapper;
import org.apache.struts.util.MessageResources;


import org.apache.struts.action.*; // expediancy


/**
 * <p>A utility class to expose the Struts shared resources,
 * which are be stored in the application, session, or
 * request contexts, as appropriate. All methods are static.</p>
 *
 * <p>This class is provided for use by Velocity context tools
 * that need access to Struts resources. By having all Struts-
 * specific code in this utility class, maintenance is simplified
 * and reuse fostered.</p>
 *
 * <p>It is the aim, that sooner or later the functionality in
 * this class is integrated into Struts itself.</p>
 *
 * @author <a href="mailto:sidler@teamup.com">Gabe Sidler</a>, based
 * on code by <a href="mailto:ted@husted.org">Ted Husted</a>
 *
 * @version $Revision: 1.1 $
 */
public class StrutsUtils
{

// ------------------------------------------------ Application Context

    /**
     * Returns the <strong>default</strong> configured data source 
     * (which must implement <code>javax.sql.DataSource</code>) or 
     * <code>null</code> if not found.
     */
    public static DataSource getDataSource(ServletContext application)
    {
        if (application==null)
        {
           return null;
        }

        return (DataSource)
            application.getAttribute(Action.DATA_SOURCE_KEY);
    }


    /* This method depends of features that are available in Struts 1.1+ only
    public static ActionMessages getActionMessages(ServletContext application) 
    {
        if (application==null)
            return null;
        return (ActionMessages)
            application.getAttribute(Action.MESSAGE_KEY);
    }
    */


    /**
     * Returns the <code>org.apache.struts.action.ActionFormBeans</code> 
     * collection for this application or <code>null</code> if not found.
     */
    public static ActionFormBeans getActionFormBeans(ServletContext application)
    {
        if (application==null)
        {
            return null;
        }

        return (ActionFormBeans)
            application.getAttribute(Action.FORM_BEANS_KEY);
    }


    /**
     * Returns the form bean definition associated with the specified
     * logical name or <code>null</code> if not found.
     *
     * @param name logical name of the requested form bean definition
     */
    public static ActionFormBean getFormBean(String name, ServletContext application)
    {
        ActionFormBeans formBeans = getActionFormBeans(application);

        if (formBeans==null)
        {
            return null;
        }

        return formBeans.findFormBean(name);
    }


    /**
     * Returns the <code>org.apache.struts.action.ActionForwards</code> 
     * collection for this application or <code>null</code> if not found.
     */
    public static ActionForwards getActionForwards(ServletContext application)
    {
        if (application==null)
        {
            return null;
        }

        return (ActionForwards)
            application.getAttribute(Action.FORWARDS_KEY);
    }


    /**
     * Returns the forwarding associated with the specified logical name
     * or <code>null</code> if not found.
     *
     * @param name Logical name of the requested forwarding
     */
    public static ActionForward getActionForward(String name, 
                                                 ServletContext application)
    {
        ActionForwards forwards = getActionForwards(application);

        if (forwards==null)
        {
            return null;
        }

        return forwards.findForward(name);
    }


    /**
     * Returns the <code>org.apache.struts.action.ActionMappings</code> 
     * collection for this application or <code>null</code> if not found.
     */
    public static ActionMappings getActionMappings(ServletContext application)
    {
        if (application==null)
        {
            return null;
        }

        return (ActionMappings)
            application.getAttribute(Action.MAPPINGS_KEY);
    }


    /**
     * Returns the mapping associated with the specified request path, 
     * or <code>null</code> if not found.
     *
     * @param path Request path for which a mapping is requested
     */
    public static ActionMapping getActionMapping(String path, 
                                                 ServletContext application)
    {
        ActionMappings mappings = getActionMappings(application);

        if (mappings==null)
        {
            return null;
        }

        return mappings.findMapping(path);
    }


    /**
     * Returns the message resources for this application or <code>null</code>
     * if not found.
     */
    public static MessageResources getMessageResources(ServletContext application)
    {
        if (application==null)
        {
            return null;
        }

        return (MessageResources)
            application.getAttribute(Action.MESSAGES_KEY);
    }


    /**
     * Returns the servlet mapping used for this application or
     * <code>null</code> if not found. The servlet mapping is
     * either a path-mapped pattern (<code>/action/*</code>) or an
     * extension mapped pattern (<code>*.do</code>).
     */
    public static String getServletMapping(ServletContext application)
    {
        if (application==null)
        {
            return null;
        }

        return (String)
            application.getAttribute(Action.SERVLET_KEY);
    }


// ---------------------------------------------------- Session Context


    /**
     * Returns the <code>java.util.Locale</code> for the user. If a 
     * locale object is not found in the user's session, the system 
     * default locale is returned.
     */
    public static Locale getLocale(HttpServletRequest request, 
                                   HttpSession session)
    {
        Locale locale = null;

        if (session!=null)
        {
            locale = (Locale) session.getAttribute(Action.LOCALE_KEY);
        }

        if ((locale==null) && (request!=null))
        {
            locale = request.getLocale();
        }

        return locale;
    }


    /**
     * Returns the transaction token stored in this session or 
     * <code>null</code> if not used.
     */
    public static String getToken(HttpSession session)
    {
        if (session==null)
        {
            return null;
        }

        return (String) session.getAttribute(Action.TRANSACTION_TOKEN_KEY);
    }


// ---------------------------------------------------- Request Context


    /**
     * Returns the <code>org.apache.struts.action.ActionErrors</code> 
     * object for this request or <code>null</code> if none exists.
     */
    public static ActionErrors getActionErrors(HttpServletRequest request)
    {
        if (request==null)
        {
            return null;
        }

        return (ActionErrors) request.getAttribute(Action.ERROR_KEY);
    }


    /**
     * Returns the runtime Exception that may have been thrown by a 
     * Struts view tool or compatible presentation extension, and 
     * placed in the request. Returns <code>null</code> if none found.
     */
    public static Throwable getException(HttpServletRequest request)
    {
        if (request==null)
        {
            return null;
        }

        return (Throwable)
            request.getAttribute(Action.EXCEPTION_KEY);
    }


    /**
     * Returns the multipart object for this request or <code>null</code>
     * if none exists.
     */
    public static MultipartRequestWrapper getMultipartRequestWrapper(HttpServletRequest request)
    {
        if (request==null)
        {
            return null;
        }

        return (MultipartRequestWrapper)
            request.getAttribute(Action.MULTIPART_KEY);
    }


   /**
     * Returns the <code>org.apache.struts.ActionMapping</code> instance 
     * for this request or <code>null</code> if none exists.
     */
    public static ActionMapping getMapping(HttpServletRequest request)
    {
        if (request==null)
        {
           return null;
        }

        return (ActionMapping)
            request.getAttribute(Action.MAPPING_KEY);
    }


    /**
     * Returns the <code>ActionForm</code> bean associated with
     * this request of <code>null</code> if none exists.
     */
    public static ActionForm getActionForm(HttpServletRequest request, 
                                           HttpSession session)
    {
        // Is there a mapping associated with this request?
        ActionMapping mapping = (ActionMapping)request.getAttribute(Action.MAPPING_KEY);
        if (mapping == null)
        {
            return (null);
        }

        // Is there a form bean associated with this mapping?
        String attribute = mapping.getAttribute();
        if (attribute == null)
        {
            return (null);
        }

        // Look up the existing form bean
        if ("request".equals(mapping.getScope()))
        {
            return (ActionForm)request.getAttribute(attribute);
        }
        if (session != null)
        {
            return (ActionForm)session.getAttribute(attribute);
        }

        return null;
    }
    

// ---------------------------------------------------- Struts Static Fields --
    
    /**
     * Returns the query parameter name under which a cancel button press 
     * must be reported if form validation is to be skipped.
     */
    public static String getCancelName()
    {
        return org.apache.struts.taglib.html.Constants.CANCEL_PROPERTY;
    }
    
    
    /**
     * Returns the query parameter name under which a transaction token
     * must be reported.
     */
    public static String getTokenName()
    {
        return org.apache.struts.taglib.html.Constants.TOKEN_KEY;
    }
 


// ------------------------------------------------ Utilities -----------------


    /**
     * Returns the form action converted into an action mapping path. The
     * value of the <code>action</code> property is manipulated as follows in
     * computing the name of the requested mapping:
     * <ul>
     * <li>Any filename extension is removed (on the theory that extension
     *     mapping is being used to select the controller servlet).</li>
     * <li>If the resulting value does not start with a slash, then a
     *     slash is prepended.</li>
     * </ul>
     */
    public static String getActionMappingName(String action)
    {

        String value = action;
        int question = action.indexOf("?");

        if (question >= 0)
        {
            value = value.substring(0, question);
        }

        int slash = value.lastIndexOf("/");
        int period = value.lastIndexOf(".");

        if ((period >= 0) && (period > slash))
        {
            value = value.substring(0, period);
        }

        if (value.startsWith("/"))
        {
            return (value);
        }
        else
        {
            return ("/" + value);
        }
    }


    /**
     * Returns the form action converted into a server-relative URI
     * reference.
     */
    public static String getActionMappingURL(ServletContext application, 
                                             HttpServletRequest request, 
                                             String action)
    {
        StringBuffer value = new StringBuffer(request.getContextPath());

        // Use our servlet mapping, if one is specified
        String servletMapping = getServletMapping(application);

        if (servletMapping != null)
        {
            String queryString = null;
            int question = action.indexOf("?");

            if (question >= 0)
            {
                queryString = action.substring(question);
            }

            String actionMapping = getActionMappingName(action);

            if (servletMapping.startsWith("*."))
            {
                value.append(actionMapping);
                value.append(servletMapping.substring(1));
            }
            else if (servletMapping.endsWith("/*"))
            {
                value.append(servletMapping.substring
                             (0, servletMapping.length() - 2));
                value.append(actionMapping);
            }

            if (queryString != null)
            {
                value.append(queryString);
            }
        }
        else
        {
            // Otherwise, assume extension mapping is in use and extension is
            // already included in the action property
            if (!action.startsWith("/"))
            {
                value.append("/");
            }

            value.append(action);
        }

        // Return the completed value
        return (value.toString());
    }


    /**
     * Returns a formatted error message. The value of message resource 
     * "errors.header" is prepended. Then, the list of error messages is 
     * rendered. The value of message resource "errors.footer" is appended.
     *
     * @param property Property name
     * @return The formatted error message. If no error messages are queued, 
     * an empty string is returned. 
     */
    public static String errorMarkup(String property, 
                                     HttpServletRequest request,
                                     HttpSession session,
                                     ServletContext application) 
    {        
        ActionErrors errors = getActionErrors(request);
        if (errors == null)
        {
            return "";
        }
        
        // fetch the error messages
        Iterator reports = null;
        if (property == null)
        {
            reports = errors.get();
        }
        else
        {
            reports = errors.get(property);
        }

        if (!(reports.hasNext()))
        {
            return "";
        }
                
        // Render the error messages appropriately if errors have been queued
        StringBuffer results = new StringBuffer();
        String header = null;
        String footer = null;
        Locale locale = getLocale(request, session);

        MessageResources resources = getMessageResources(application);
        if (resources != null)
        {
            header = resources.getMessage(locale, "errors.header");
            footer = resources.getMessage(locale, "errors.footer");
        }
        if (header == null)
        {
            header = "errors.header";
        }
        if (footer == null)
        {
            footer = "errors.footer";    
        }
                     
        results.append(header);
        results.append("\r\n");

        String message;
        while (reports.hasNext()) 
        {
            message = null;
            ActionError report = (ActionError) reports.next();
            if (resources != null)
            {
                message = resources.getMessage(locale,
                                               report.getKey(),
                                               report.getValues());
            }
            if (message != null) 
            {
                results.append(message);
                results.append("\r\n");
            }
            else
            {
                results.append(report.getKey());
                results.append("\r\n");
            }
        }

        results.append(footer);
        results.append("\r\n");

        // return result
        return results.toString();
    }
 
}
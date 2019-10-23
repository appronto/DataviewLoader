// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package randomuserapi.proxies;

public class Login
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject loginMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "RandomUserAPI.Login";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Username("Username"),
		Password("Password"),
		Login_JsonObject("RandomUserAPI.Login_JsonObject");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public Login(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "RandomUserAPI.Login"));
	}

	protected Login(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject loginMendixObject)
	{
		if (loginMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("RandomUserAPI.Login", loginMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a RandomUserAPI.Login");

		this.loginMendixObject = loginMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Login.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static randomuserapi.proxies.Login initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return randomuserapi.proxies.Login.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static randomuserapi.proxies.Login initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new randomuserapi.proxies.Login(context, mendixObject);
	}

	public static randomuserapi.proxies.Login load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return randomuserapi.proxies.Login.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of Username
	 */
	public final java.lang.String getUsername()
	{
		return getUsername(getContext());
	}

	/**
	 * @param context
	 * @return value of Username
	 */
	public final java.lang.String getUsername(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Username.toString());
	}

	/**
	 * Set value of Username
	 * @param username
	 */
	public final void setUsername(java.lang.String username)
	{
		setUsername(getContext(), username);
	}

	/**
	 * Set value of Username
	 * @param context
	 * @param username
	 */
	public final void setUsername(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String username)
	{
		getMendixObject().setValue(context, MemberNames.Username.toString(), username);
	}

	/**
	 * @return value of Password
	 */
	public final java.lang.String getPassword()
	{
		return getPassword(getContext());
	}

	/**
	 * @param context
	 * @return value of Password
	 */
	public final java.lang.String getPassword(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Password.toString());
	}

	/**
	 * Set value of Password
	 * @param password
	 */
	public final void setPassword(java.lang.String password)
	{
		setPassword(getContext(), password);
	}

	/**
	 * Set value of Password
	 * @param context
	 * @param password
	 */
	public final void setPassword(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String password)
	{
		getMendixObject().setValue(context, MemberNames.Password.toString(), password);
	}

	/**
	 * @return value of Login_JsonObject
	 */
	public final randomuserapi.proxies.User getLogin_JsonObject() throws com.mendix.core.CoreException
	{
		return getLogin_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Login_JsonObject
	 */
	public final randomuserapi.proxies.User getLogin_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.User result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Login_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.User.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Login_JsonObject
	 * @param login_jsonobject
	 */
	public final void setLogin_JsonObject(randomuserapi.proxies.User login_jsonobject)
	{
		setLogin_JsonObject(getContext(), login_jsonobject);
	}

	/**
	 * Set value of Login_JsonObject
	 * @param context
	 * @param login_jsonobject
	 */
	public final void setLogin_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.User login_jsonobject)
	{
		if (login_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Login_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Login_JsonObject.toString(), login_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return loginMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final randomuserapi.proxies.Login that = (randomuserapi.proxies.Login) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "RandomUserAPI.Login";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}

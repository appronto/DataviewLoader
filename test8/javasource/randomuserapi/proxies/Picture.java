// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package randomuserapi.proxies;

public class Picture
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject pictureMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "RandomUserAPI.Picture";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Large("Large"),
		Medium("Medium"),
		Thumbnail("Thumbnail"),
		Picture_JsonObject("RandomUserAPI.Picture_JsonObject");

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

	public Picture(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "RandomUserAPI.Picture"));
	}

	protected Picture(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject pictureMendixObject)
	{
		if (pictureMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("RandomUserAPI.Picture", pictureMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a RandomUserAPI.Picture");

		this.pictureMendixObject = pictureMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Picture.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static randomuserapi.proxies.Picture initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return randomuserapi.proxies.Picture.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static randomuserapi.proxies.Picture initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new randomuserapi.proxies.Picture(context, mendixObject);
	}

	public static randomuserapi.proxies.Picture load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return randomuserapi.proxies.Picture.initialize(context, mendixObject);
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
	 * @return value of Large
	 */
	public final java.lang.String getLarge()
	{
		return getLarge(getContext());
	}

	/**
	 * @param context
	 * @return value of Large
	 */
	public final java.lang.String getLarge(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Large.toString());
	}

	/**
	 * Set value of Large
	 * @param large
	 */
	public final void setLarge(java.lang.String large)
	{
		setLarge(getContext(), large);
	}

	/**
	 * Set value of Large
	 * @param context
	 * @param large
	 */
	public final void setLarge(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String large)
	{
		getMendixObject().setValue(context, MemberNames.Large.toString(), large);
	}

	/**
	 * @return value of Medium
	 */
	public final java.lang.String getMedium()
	{
		return getMedium(getContext());
	}

	/**
	 * @param context
	 * @return value of Medium
	 */
	public final java.lang.String getMedium(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Medium.toString());
	}

	/**
	 * Set value of Medium
	 * @param medium
	 */
	public final void setMedium(java.lang.String medium)
	{
		setMedium(getContext(), medium);
	}

	/**
	 * Set value of Medium
	 * @param context
	 * @param medium
	 */
	public final void setMedium(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String medium)
	{
		getMendixObject().setValue(context, MemberNames.Medium.toString(), medium);
	}

	/**
	 * @return value of Thumbnail
	 */
	public final java.lang.String getThumbnail()
	{
		return getThumbnail(getContext());
	}

	/**
	 * @param context
	 * @return value of Thumbnail
	 */
	public final java.lang.String getThumbnail(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Thumbnail.toString());
	}

	/**
	 * Set value of Thumbnail
	 * @param thumbnail
	 */
	public final void setThumbnail(java.lang.String thumbnail)
	{
		setThumbnail(getContext(), thumbnail);
	}

	/**
	 * Set value of Thumbnail
	 * @param context
	 * @param thumbnail
	 */
	public final void setThumbnail(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String thumbnail)
	{
		getMendixObject().setValue(context, MemberNames.Thumbnail.toString(), thumbnail);
	}

	/**
	 * @return value of Picture_JsonObject
	 */
	public final randomuserapi.proxies.User getPicture_JsonObject() throws com.mendix.core.CoreException
	{
		return getPicture_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Picture_JsonObject
	 */
	public final randomuserapi.proxies.User getPicture_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.User result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Picture_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.User.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Picture_JsonObject
	 * @param picture_jsonobject
	 */
	public final void setPicture_JsonObject(randomuserapi.proxies.User picture_jsonobject)
	{
		setPicture_JsonObject(getContext(), picture_jsonobject);
	}

	/**
	 * Set value of Picture_JsonObject
	 * @param context
	 * @param picture_jsonobject
	 */
	public final void setPicture_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.User picture_jsonobject)
	{
		if (picture_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Picture_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Picture_JsonObject.toString(), picture_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return pictureMendixObject;
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
			final randomuserapi.proxies.Picture that = (randomuserapi.proxies.Picture) obj;
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
		return "RandomUserAPI.Picture";
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
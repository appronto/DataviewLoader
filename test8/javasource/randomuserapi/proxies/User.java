// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package randomuserapi.proxies;

public class User
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject userMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "RandomUserAPI.User";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Gender("Gender"),
		Email("Email"),
		Dob("Dob"),
		Registered("Registered"),
		Phone("Phone"),
		Cell("Cell"),
		Nat("Nat"),
		Name_JsonObject("RandomUserAPI.Name_JsonObject"),
		Location_JsonObject("RandomUserAPI.Location_JsonObject"),
		Login_JsonObject("RandomUserAPI.Login_JsonObject"),
		_id_JsonObject("RandomUserAPI._id_JsonObject"),
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

	public User(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "RandomUserAPI.User"));
	}

	protected User(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject userMendixObject)
	{
		if (userMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("RandomUserAPI.User", userMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a RandomUserAPI.User");

		this.userMendixObject = userMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'User.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static randomuserapi.proxies.User initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return randomuserapi.proxies.User.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static randomuserapi.proxies.User initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new randomuserapi.proxies.User(context, mendixObject);
	}

	public static randomuserapi.proxies.User load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return randomuserapi.proxies.User.initialize(context, mendixObject);
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
	 * @return value of Gender
	 */
	public final java.lang.String getGender()
	{
		return getGender(getContext());
	}

	/**
	 * @param context
	 * @return value of Gender
	 */
	public final java.lang.String getGender(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Gender.toString());
	}

	/**
	 * Set value of Gender
	 * @param gender
	 */
	public final void setGender(java.lang.String gender)
	{
		setGender(getContext(), gender);
	}

	/**
	 * Set value of Gender
	 * @param context
	 * @param gender
	 */
	public final void setGender(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String gender)
	{
		getMendixObject().setValue(context, MemberNames.Gender.toString(), gender);
	}

	/**
	 * @return value of Email
	 */
	public final java.lang.String getEmail()
	{
		return getEmail(getContext());
	}

	/**
	 * @param context
	 * @return value of Email
	 */
	public final java.lang.String getEmail(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Email.toString());
	}

	/**
	 * Set value of Email
	 * @param email
	 */
	public final void setEmail(java.lang.String email)
	{
		setEmail(getContext(), email);
	}

	/**
	 * Set value of Email
	 * @param context
	 * @param email
	 */
	public final void setEmail(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String email)
	{
		getMendixObject().setValue(context, MemberNames.Email.toString(), email);
	}

	/**
	 * @return value of Dob
	 */
	public final java.lang.String getDob()
	{
		return getDob(getContext());
	}

	/**
	 * @param context
	 * @return value of Dob
	 */
	public final java.lang.String getDob(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Dob.toString());
	}

	/**
	 * Set value of Dob
	 * @param dob
	 */
	public final void setDob(java.lang.String dob)
	{
		setDob(getContext(), dob);
	}

	/**
	 * Set value of Dob
	 * @param context
	 * @param dob
	 */
	public final void setDob(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String dob)
	{
		getMendixObject().setValue(context, MemberNames.Dob.toString(), dob);
	}

	/**
	 * @return value of Registered
	 */
	public final java.lang.String getRegistered()
	{
		return getRegistered(getContext());
	}

	/**
	 * @param context
	 * @return value of Registered
	 */
	public final java.lang.String getRegistered(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Registered.toString());
	}

	/**
	 * Set value of Registered
	 * @param registered
	 */
	public final void setRegistered(java.lang.String registered)
	{
		setRegistered(getContext(), registered);
	}

	/**
	 * Set value of Registered
	 * @param context
	 * @param registered
	 */
	public final void setRegistered(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String registered)
	{
		getMendixObject().setValue(context, MemberNames.Registered.toString(), registered);
	}

	/**
	 * @return value of Phone
	 */
	public final java.lang.String getPhone()
	{
		return getPhone(getContext());
	}

	/**
	 * @param context
	 * @return value of Phone
	 */
	public final java.lang.String getPhone(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Phone.toString());
	}

	/**
	 * Set value of Phone
	 * @param phone
	 */
	public final void setPhone(java.lang.String phone)
	{
		setPhone(getContext(), phone);
	}

	/**
	 * Set value of Phone
	 * @param context
	 * @param phone
	 */
	public final void setPhone(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String phone)
	{
		getMendixObject().setValue(context, MemberNames.Phone.toString(), phone);
	}

	/**
	 * @return value of Cell
	 */
	public final java.lang.String getCell()
	{
		return getCell(getContext());
	}

	/**
	 * @param context
	 * @return value of Cell
	 */
	public final java.lang.String getCell(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Cell.toString());
	}

	/**
	 * Set value of Cell
	 * @param cell
	 */
	public final void setCell(java.lang.String cell)
	{
		setCell(getContext(), cell);
	}

	/**
	 * Set value of Cell
	 * @param context
	 * @param cell
	 */
	public final void setCell(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String cell)
	{
		getMendixObject().setValue(context, MemberNames.Cell.toString(), cell);
	}

	/**
	 * @return value of Nat
	 */
	public final java.lang.String getNat()
	{
		return getNat(getContext());
	}

	/**
	 * @param context
	 * @return value of Nat
	 */
	public final java.lang.String getNat(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Nat.toString());
	}

	/**
	 * Set value of Nat
	 * @param nat
	 */
	public final void setNat(java.lang.String nat)
	{
		setNat(getContext(), nat);
	}

	/**
	 * Set value of Nat
	 * @param context
	 * @param nat
	 */
	public final void setNat(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String nat)
	{
		getMendixObject().setValue(context, MemberNames.Nat.toString(), nat);
	}

	/**
	 * @return value of Name_JsonObject
	 */
	public final randomuserapi.proxies.Name getName_JsonObject() throws com.mendix.core.CoreException
	{
		return getName_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Name_JsonObject
	 */
	public final randomuserapi.proxies.Name getName_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.Name result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Name_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.Name.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Name_JsonObject
	 * @param name_jsonobject
	 */
	public final void setName_JsonObject(randomuserapi.proxies.Name name_jsonobject)
	{
		setName_JsonObject(getContext(), name_jsonobject);
	}

	/**
	 * Set value of Name_JsonObject
	 * @param context
	 * @param name_jsonobject
	 */
	public final void setName_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.Name name_jsonobject)
	{
		if (name_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Name_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Name_JsonObject.toString(), name_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return value of Location_JsonObject
	 */
	public final randomuserapi.proxies.Location getLocation_JsonObject() throws com.mendix.core.CoreException
	{
		return getLocation_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Location_JsonObject
	 */
	public final randomuserapi.proxies.Location getLocation_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.Location result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Location_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.Location.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Location_JsonObject
	 * @param location_jsonobject
	 */
	public final void setLocation_JsonObject(randomuserapi.proxies.Location location_jsonobject)
	{
		setLocation_JsonObject(getContext(), location_jsonobject);
	}

	/**
	 * Set value of Location_JsonObject
	 * @param context
	 * @param location_jsonobject
	 */
	public final void setLocation_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.Location location_jsonobject)
	{
		if (location_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Location_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Location_JsonObject.toString(), location_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return value of Login_JsonObject
	 */
	public final randomuserapi.proxies.Login getLogin_JsonObject() throws com.mendix.core.CoreException
	{
		return getLogin_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Login_JsonObject
	 */
	public final randomuserapi.proxies.Login getLogin_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.Login result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Login_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.Login.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Login_JsonObject
	 * @param login_jsonobject
	 */
	public final void setLogin_JsonObject(randomuserapi.proxies.Login login_jsonobject)
	{
		setLogin_JsonObject(getContext(), login_jsonobject);
	}

	/**
	 * Set value of Login_JsonObject
	 * @param context
	 * @param login_jsonobject
	 */
	public final void setLogin_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.Login login_jsonobject)
	{
		if (login_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Login_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Login_JsonObject.toString(), login_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return value of _id_JsonObject
	 */
	public final randomuserapi.proxies._id get_id_JsonObject() throws com.mendix.core.CoreException
	{
		return get_id_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of _id_JsonObject
	 */
	public final randomuserapi.proxies._id get_id_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies._id result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames._id_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies._id.load(context, identifier);
		return result;
	}

	/**
	 * Set value of _id_JsonObject
	 * @param _id_jsonobject
	 */
	public final void set_id_JsonObject(randomuserapi.proxies._id _id_jsonobject)
	{
		set_id_JsonObject(getContext(), _id_jsonobject);
	}

	/**
	 * Set value of _id_JsonObject
	 * @param context
	 * @param _id_jsonobject
	 */
	public final void set_id_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies._id _id_jsonobject)
	{
		if (_id_jsonobject == null)
			getMendixObject().setValue(context, MemberNames._id_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames._id_JsonObject.toString(), _id_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return value of Picture_JsonObject
	 */
	public final randomuserapi.proxies.Picture getPicture_JsonObject() throws com.mendix.core.CoreException
	{
		return getPicture_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Picture_JsonObject
	 */
	public final randomuserapi.proxies.Picture getPicture_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		randomuserapi.proxies.Picture result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Picture_JsonObject.toString());
		if (identifier != null)
			result = randomuserapi.proxies.Picture.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Picture_JsonObject
	 * @param picture_jsonobject
	 */
	public final void setPicture_JsonObject(randomuserapi.proxies.Picture picture_jsonobject)
	{
		setPicture_JsonObject(getContext(), picture_jsonobject);
	}

	/**
	 * Set value of Picture_JsonObject
	 * @param context
	 * @param picture_jsonobject
	 */
	public final void setPicture_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, randomuserapi.proxies.Picture picture_jsonobject)
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
		return userMendixObject;
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
			final randomuserapi.proxies.User that = (randomuserapi.proxies.User) obj;
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
		return "RandomUserAPI.User";
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

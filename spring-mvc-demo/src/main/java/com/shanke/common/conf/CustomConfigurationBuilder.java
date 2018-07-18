/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shanke.common.conf;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationBuilder;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationRuntimeException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.JNDIConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.XMLPropertiesConfiguration;
import org.apache.commons.configuration.beanutils.BeanDeclaration;
import org.apache.commons.configuration.beanutils.BeanFactory;
import org.apache.commons.configuration.beanutils.BeanHelper;
import org.apache.commons.configuration.beanutils.DefaultBeanFactory;
import org.apache.commons.configuration.beanutils.XMLBeanDeclaration;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.configuration.plist.XMLPropertyListConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.configuration.tree.DefaultExpressionEngine;
import org.apache.commons.configuration.tree.OverrideCombiner;
import org.apache.commons.configuration.tree.UnionCombiner;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

/**
 * <pre>
 * Description: 
 *       * A factory class that creates a composite configuration from an XML based
 * <em>configuration definition file</em>.
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author yjw
 * @date 2015-12-5 下午10:17:09
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class CustomConfigurationBuilder extends XMLConfiguration implements
		ConfigurationBuilder {
	/**
	 * Constant for the name of the additional configuration. If the
	 * configuration definition file contains an <code>additional</code>
	 * section, a special union configuration is created and added under this
	 * name to the resulting combined configuration.
	 */
	public static final String ADDITIONAL_NAME = CustomConfigurationBuilder.class
			.getName() + "/ADDITIONAL_CONFIG";

	/** Constant for the expression engine used by this builder. */
	static final XPathExpressionEngine EXPRESSION_ENGINE = new XPathExpressionEngine();

	/** Constant for the name of the configuration bean factory. */
	static final String CONFIG_BEAN_FACTORY_NAME = CustomConfigurationBuilder.class
			.getName() + ".CONFIG_BEAN_FACTORY_NAME";

	/** Constant for the reserved name attribute. */
	static final String ATTR_NAME = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ XMLBeanDeclaration.RESERVED_PREFIX
			+ "name"
			+ DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the name of the at attribute. */
	static final String ATTR_ATNAME = "at";

	/** Constant for the reserved at attribute. */
	static final String ATTR_AT_RES = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ XMLBeanDeclaration.RESERVED_PREFIX
			+ ATTR_ATNAME
			+ DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the at attribute without the reserved prefix. */
	static final String ATTR_AT = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ ATTR_ATNAME + DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the name of the optional attribute. */
	static final String ATTR_OPTIONALNAME = "optional";

	/** Constant for the reserved optional attribute. */
	static final String ATTR_OPTIONAL_RES = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ XMLBeanDeclaration.RESERVED_PREFIX
			+ ATTR_OPTIONALNAME
			+ DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the optional attribute without the reserved prefix. */
	static final String ATTR_OPTIONAL = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ ATTR_OPTIONALNAME + DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the file name attribute. */
	static final String ATTR_FILENAME = DefaultExpressionEngine.DEFAULT_ATTRIBUTE_START
			+ "fileName" + DefaultExpressionEngine.DEFAULT_ATTRIBUTE_END;

	/** Constant for the name of the header section. */
	static final String SEC_HEADER = "header";

	/** Constant for an expression that selects the union configurations. */
	static final String KEY_UNION = "/additional/*";

	/** Constant for an expression that selects override configurations. */
	static final String KEY_OVERRIDE1 = "/*[local-name() != 'additional' and "
			+ "local-name() != 'override' and local-name() != '" + SEC_HEADER
			+ "']";

	/**
	 * Constant for an expression that selects override configurations in the
	 * override section.
	 */
	static final String KEY_OVERRIDE2 = "/override/*";

	/**
	 * Constant for the key that points to the list nodes definition of the
	 * override combiner.
	 */
	static final String KEY_OVERRIDE_LIST = SEC_HEADER
			+ "/combiner/override/list-nodes/node";

	/**
	 * Constant for the key that points to the list nodes definition of the
	 * additional combiner.
	 */
	static final String KEY_ADDITIONAL_LIST = SEC_HEADER
			+ "/combiner/additional/list-nodes/node";

	/**
	 * Constant for the key of the result declaration. This key can point to a
	 * bean declaration, which defines properties of the resulting combined
	 * configuration.
	 */
	static final String KEY_RESULT = SEC_HEADER + "/result";

	/** Constant for the key of the combiner in the result declaration. */
	static final String KEY_COMBINER = KEY_RESULT + "/nodeCombiner";

	/** Constant for the XML file extension. */
	static final String EXT_XML = ".xml";

	/** Constant for the provider for properties files. */
	private static final ConfigurationProvider PROPERTIES_PROVIDER = new FileExtensionConfigurationProvider(
			XMLPropertiesConfiguration.class, PropertiesConfiguration.class,
			EXT_XML);

	/** Constant for the provider for XML files. */
	private static final ConfigurationProvider XML_PROVIDER = new FileConfigurationProvider(
			XMLConfiguration.class);

	/** Constant for the provider for JNDI sources. */
	private static final ConfigurationProvider JNDI_PROVIDER = new ConfigurationProvider(
			JNDIConfiguration.class);

	/** Constant for the provider for system properties. */
	private static final ConfigurationProvider SYSTEM_PROVIDER = new ConfigurationProvider(
			SystemConfiguration.class);

	/** Constant for the provider for plist files. */
	private static final ConfigurationProvider PLIST_PROVIDER = new FileExtensionConfigurationProvider(
			XMLPropertyListConfiguration.class,
			PropertyListConfiguration.class, EXT_XML);

	/** Constant for the provider for configuration definition files. */
	private static final ConfigurationProvider BUILDER_PROVIDER = new ConfigurationBuilderProvider();

	/** An array with the names of the default tags. */
	private static final String[] DEFAULT_TAGS = { "properties", "xml",
			"hierarchicalXml", "jndi", "system", "plist", "configuration" };

	/** An array with the providers for the default tags. */
	private static final ConfigurationProvider[] DEFAULT_PROVIDERS = {
			PROPERTIES_PROVIDER, XML_PROVIDER, XML_PROVIDER, JNDI_PROVIDER,
			SYSTEM_PROVIDER, PLIST_PROVIDER, BUILDER_PROVIDER };

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -3113777854714492123L;

	/** Stores the configuration that is currently constructed. */
	private Configuration constructedConfiguration;

	/** Stores a map with the registered configuration providers. */
	private Map providers;

	/** Stores the base path to the configuration sources to load. */
	private String configurationBasePath;

	/**
	 * Creates a new instance of <code>CustomConfigurationBuilder</code>. A
	 * configuration definition file is not yet loaded. Use the diverse setter
	 * methods provided by file based configurations to specify the
	 * configuration definition file.
	 */
	public CustomConfigurationBuilder() {
		super();
		providers = new HashMap();
		setExpressionEngine(EXPRESSION_ENGINE);
		registerDefaultProviders();
	}

	/**
	 * Creates a new instance of <code>CustomConfigurationBuilder</code> and
	 * sets the specified configuration definition file.
	 * 
	 * @param file
	 *            the configuration definition file
	 */
	public CustomConfigurationBuilder(File file) {
		this();
		setFile(file);
	}

	/**
	 * Creates a new instance of <code>CustomConfigurationBuilder</code> and
	 * sets the specified configuration definition file.
	 * 
	 * @param fileName
	 *            the name of the configuration definition file
	 * @throws ConfigurationException
	 *             if an error occurs when the file is loaded
	 */
	public CustomConfigurationBuilder(String fileName)
			throws ConfigurationException {
		this();
		setFileName(fileName);
	}

	/**
	 * Creates a new instance of <code>CustomConfigurationBuilder</code> and
	 * sets the specified configuration definition file.
	 * 
	 * @param url
	 *            the URL to the configuration definition file
	 * @throws ConfigurationException
	 *             if an error occurs when the file is loaded
	 */
	public CustomConfigurationBuilder(URL url) throws ConfigurationException {
		this();
		setURL(url);
	}

	/**
	 * Returns the base path for the configuration sources to load. This path is
	 * used to resolve relative paths in the configuration definition file.
	 * 
	 * @return the base path for configuration sources
	 */
	public String getConfigurationBasePath() {
		return (configurationBasePath != null) ? configurationBasePath
				: getBasePath();
	}

	/**
	 * Sets the base path for the configuration sources to load. Normally a base
	 * path need not to be set because it is determined by the location of the
	 * configuration definition file to load. All relative pathes in this file
	 * are resolved relative to this file. Setting a base path makes sense if
	 * such relative pathes should be otherwise resolved, e.g. if the
	 * configuration file is loaded from the class path and all sub
	 * configurations it refers to are stored in a special config directory.
	 * 
	 * @param configurationBasePath
	 *            the new base path to set
	 */
	public void setConfigurationBasePath(String configurationBasePath) {
		this.configurationBasePath = configurationBasePath;
	}

	/**
	 * Adds a configuration provider for the specified tag. Whenever this tag is
	 * encountered in the configuration definition file this provider will be
	 * called to create the configuration object.
	 * 
	 * @param tagName
	 *            the name of the tag in the configuration definition file
	 * @param provider
	 *            the provider for this tag
	 */
	public void addConfigurationProvider(String tagName,
			ConfigurationProvider provider) {
		if (tagName == null) {
			throw new IllegalArgumentException("Tag name must not be null!");
		}
		if (provider == null) {
			throw new IllegalArgumentException("Provider must not be null!");
		}

		providers.put(tagName, provider);
	}

	/**
	 * Removes the configuration provider for the specified tag name.
	 * 
	 * @param tagName
	 *            the tag name
	 * @return the removed configuration provider or <b>null</b> if none was
	 *         registered for that tag
	 */
	public ConfigurationProvider removeConfigurationProvider(String tagName) {
		return (ConfigurationProvider) providers.remove(tagName);
	}

	/**
	 * Returns the configuration provider for the given tag.
	 * 
	 * @param tagName
	 *            the name of the tag
	 * @return the provider that was registered for this tag or <b>null</b> if
	 *         there is none
	 */
	public ConfigurationProvider providerForTag(String tagName) {
		return (ConfigurationProvider) providers.get(tagName);
	}

	/**
	 * Returns the configuration provided by this builder. Loads and parses the
	 * configuration definition file and creates instances for the declared
	 * configurations.
	 * 
	 * @return the configuration
	 * @throws ConfigurationException
	 *             if an error occurs
	 */
	public Configuration getConfiguration() throws ConfigurationException {
		return getConfiguration(true);
	}

	/**
	 * Returns the configuration provided by this builder. If the boolean
	 * parameter is <b>true</b>, the configuration definition file will be
	 * loaded. It will then be parsed, and instances for the declared
	 * configurations will be created.
	 * 
	 * @param load
	 *            a flag whether the configuration definition file should be
	 *            loaded; a value of <b>false</b> would make sense if the file
	 *            has already been created or its content was manipulated using
	 *            some of the property accessor methods
	 * @return the configuration
	 * @throws ConfigurationException
	 *             if an error occurs
	 */
	public Configuration getConfiguration(boolean load)
			throws ConfigurationException {
		if (load) {
			load();
		}

		Configuration result = createResultConfiguration();
		constructedConfiguration = result;

		List overrides = configurationsAt(KEY_OVERRIDE1);
		overrides.addAll(configurationsAt(KEY_OVERRIDE2));
		initCombinedConfiguration(result, overrides, KEY_OVERRIDE_LIST);

		List additionals = configurationsAt(KEY_UNION);
		if (!additionals.isEmpty()) {
			CombinedConfiguration addConfig = new CombinedConfiguration(
					new UnionCombiner());
			result.addConfiguration(addConfig, ADDITIONAL_NAME);
			initCombinedConfiguration(addConfig, additionals,
					KEY_ADDITIONAL_LIST);
		}

		return result;
	}

	/**
	 * Creates the resulting combined configuration. This method is called by
	 * <code>getConfiguration()</code>. It checks whether the
	 * <code>header</code> section of the configuration definition file contains
	 * a <code>result</code> element. If this is the case, it will be used to
	 * initialize the properties of the newly created configuration object.
	 * 
	 * @return the resulting configuration object
	 * @throws ConfigurationException
	 *             if an error occurs
	 */
	protected Configuration createResultConfiguration()
			throws ConfigurationException {
		XMLBeanDeclaration decl = new XMLBeanDeclaration(this, KEY_RESULT, true);
		Configuration result = Reflection.newInstance(Configuration.class);

		if (getMaxIndex(KEY_COMBINER) < 0) {
			// No combiner defined => set default
			result.setNodeCombiner(new OverrideCombiner());
		}

		return result;
	}

	/**
	 * Initializes a combined configuration for the configurations of a specific
	 * section. This method is called for the override and for the additional
	 * section (if it exists).
	 * 
	 * @param config
	 *            the configuration to be initialized
	 * @param containedConfigs
	 *            the list with the declaratinos of the contained configurations
	 * @param keyListNodes
	 *            a list with the declaration of list nodes
	 * @throws ConfigurationException
	 *             if an error occurs
	 */
	protected void initCombinedConfiguration(CombinedConfiguration config,
			List containedConfigs, String keyListNodes)
			throws ConfigurationException {
		List listNodes = getList(keyListNodes);
		for (Iterator it = listNodes.iterator(); it.hasNext();) {
			config.getNodeCombiner().addListNode((String) it.next());
		}

		for (Iterator it = containedConfigs.iterator(); it.hasNext();) {
			HierarchicalConfiguration conf = (HierarchicalConfiguration) it
					.next();
			ConfigurationDeclaration decl = new ConfigurationDeclaration(this,
					conf);
			AbstractConfiguration newConf = createConfigurationAt(decl);
			if (newConf != null) {
				config.addConfiguration(newConf, decl.getConfiguration()
						.getString(ATTR_NAME), decl.getAt());
			}
		}
	}

	/**
	 * Registers the default configuration providers supported by this class.
	 * This method will be called during initialization. It registers
	 * configuration providers for the tags that are supported by default.
	 */
	protected void registerDefaultProviders() {
		for (int i = 0; i < DEFAULT_TAGS.length; i++) {
			addConfigurationProvider(DEFAULT_TAGS[i], DEFAULT_PROVIDERS[i]);
		}
	}

	/**
	 * Performs interpolation. This method will not only take this configuration
	 * instance into account (which is the one that loaded the configuration
	 * definition file), but also the so far constructed combined configuration.
	 * So variables can be used that point to properties that are defined in
	 * configuration sources loaded by this builder.
	 * 
	 * @param value
	 *            the value to be interpolated
	 * @return the interpolated value
	 */
	protected Object interpolate(Object value) {
		Object result = super.interpolate(value);
		if (constructedConfiguration != null) {
			result = constructedConfiguration.interpolate(result);
		}
		return result;
	}

	/**
	 * Creates a configuration object from the specified configuration
	 * declaration.
	 * 
	 * @param decl
	 *            the configuration declaration
	 * @return the new configuration object
	 * @throws ConfigurationException
	 *             if an error occurs
	 */
	private AbstractConfiguration createConfigurationAt(
			ConfigurationDeclaration decl) throws ConfigurationException {
		try {
			return (AbstractConfiguration) BeanHelper.createBean(decl);
		} catch (Exception ex) {
			// redirect to configuration exceptions
			throw new ConfigurationException(ex);
		}
	}

	/**
	 * <p>
	 * A base class for creating and initializing configuration sources.
	 * </p>
	 * <p>
	 * Concrete sub classes of this base class are responsible for creating
	 * specific <code>Configuration</code> objects for the tags in the
	 * configuration definition file. The configuration factory will parse the
	 * definition file and try to find a matching
	 * <code>ConfigurationProvider</code> for each encountered tag. This
	 * provider is then asked to create a corresponding
	 * <code>Configuration</code> object. It is up to a concrete implementation
	 * how this object is created and initialized.
	 * </p>
	 * <p>
	 * Note that at the moment only configuration classes derived from
	 * <code>{@link AbstractConfiguration}</code> are supported.
	 * </p>
	 */
	public static class ConfigurationProvider extends DefaultBeanFactory {
		/** Stores the class of the configuration to be created. */
		private Class configurationClass;

		/**
		 * Creates a new uninitialized instance of
		 * <code>ConfigurationProvider</code>.
		 */
		public ConfigurationProvider() {
			this(null);
		}

		/**
		 * Creates a new instance of <code>ConfigurationProvider</code> and sets
		 * the class of the configuration created by this provider.
		 * 
		 * @param configClass
		 *            the configuration class
		 */
		public ConfigurationProvider(Class configClass) {
			setConfigurationClass(configClass);
		}

		/**
		 * Returns the class of the configuration returned by this provider.
		 * 
		 * @return the class of the provided configuration
		 */
		public Class getConfigurationClass() {
			return configurationClass;
		}

		/**
		 * Sets the class of the configuration returned by this provider.
		 * 
		 * @param configurationClass
		 *            the configuration class
		 */
		public void setConfigurationClass(Class configurationClass) {
			this.configurationClass = configurationClass;
		}

		/**
		 * Returns the configuration. This method is called to fetch the
		 * configuration from the provider. This implementation will call the
		 * inherited
		 * <code>{@link org.apache.commons.configuration.beanutils.DefaultBeanFactory#createBean(Class, BeanDeclaration, Object) createBean()}</code>
		 * method to create a new instance of the configuration class.
		 * 
		 * @param decl
		 *            the bean declaration with initialization parameters for
		 *            the configuration
		 * @return the new configuration object
		 * @throws Exception
		 *             if an error occurs
		 */
		public AbstractConfiguration getConfiguration(
				ConfigurationDeclaration decl) throws Exception {
			return (AbstractConfiguration) createBean(getConfigurationClass(),
					decl, null);
		}
	}

	/**
	 * <p>
	 * A specialized <code>BeanDeclaration</code> implementation that represents
	 * the declaration of a configuration source.
	 * </p>
	 * <p>
	 * Instances of this class are able to extract all information about a
	 * configuration source from the configuration definition file. The
	 * declaration of a configuration source is very similar to a bean
	 * declaration processed by <code>XMLBeanDeclaration</code>. There are very
	 * few differences, e.g. the two reserved attributes <code>optional</code>
	 * and <code>at</code> and the fact that a bean factory is never needed.
	 * </p>
	 */
	protected static class ConfigurationDeclaration extends XMLBeanDeclaration {
		/** Stores a reference to the associated configuration builder. */
		private CustomConfigurationBuilder configurationBuilder;

		/**
		 * Creates a new instance of <code>ConfigurationDeclaration</code> and
		 * initializes it.
		 * 
		 * @param builder
		 *            the associated configuration builder
		 * @param config
		 *            the configuration this declaration is based onto
		 */
		public ConfigurationDeclaration(CustomConfigurationBuilder builder,
				HierarchicalConfiguration config) {
			super(config);
			configurationBuilder = builder;
		}

		/**
		 * Returns the associated configuration builder.
		 * 
		 * @return the configuration builder
		 */
		public CustomConfigurationBuilder getConfigurationBuilder() {
			return configurationBuilder;
		}

		/**
		 * Returns the value of the <code>at</code> attribute.
		 * 
		 * @return the value of the <code>at</code> attribute (can be
		 *         <b>null</b>)
		 */
		public String getAt() {
			String result = this.getConfiguration().getString(ATTR_AT_RES);
			return (result == null) ? this.getConfiguration()
					.getString(ATTR_AT) : result;
		}

		/**
		 * Returns a flag whether this is an optional configuration.
		 * 
		 * @return a flag if this declaration points to an optional
		 *         configuration
		 */
		public boolean isOptional() {
			Boolean value = this.getConfiguration().getBoolean(
					ATTR_OPTIONAL_RES, null);
			if (value == null) {
				value = this.getConfiguration().getBoolean(ATTR_OPTIONAL,
						Boolean.FALSE);
			}
			return value.booleanValue();
		}

		/**
		 * Returns the name of the bean factory. For configuration source
		 * declarations always a reserved factory is used. This factory's name
		 * is returned by this implementation.
		 * 
		 * @return the name of the bean factory
		 */
		public String getBeanFactoryName() {
			return CONFIG_BEAN_FACTORY_NAME;
		}

		/**
		 * Returns the bean's class name. This implementation will always return
		 * <b>null</b>.
		 * 
		 * @return the name of the bean's class
		 */
		public String getBeanClassName() {
			return null;
		}

		/**
		 * Checks whether the given node is reserved. This method will take
		 * further reserved attributes into account
		 * 
		 * @param nd
		 *            the node
		 * @return a flag whether this node is reserved
		 */
		protected boolean isReservedNode(ConfigurationNode nd) {
			if (super.isReservedNode(nd)) {
				return true;
			}

			return nd.isAttribute()
					&& ((ATTR_ATNAME.equals(nd.getName()) && nd.getParentNode()
							.getAttributeCount(RESERVED_PREFIX + ATTR_ATNAME) == 0) || (ATTR_OPTIONALNAME
							.equals(nd.getName()) && nd.getParentNode()
							.getAttributeCount(
									RESERVED_PREFIX + ATTR_OPTIONALNAME) == 0));
		}

		/**
		 * Performs interpolation. This implementation will delegate
		 * interpolation to the configuration builder, which takes care that the
		 * currently constructed configuration is taken into account, too.
		 * 
		 * @param value
		 *            the value to be interpolated
		 * @return the interpolated value
		 */
		protected Object interpolate(Object value) {
			return getConfigurationBuilder().interpolate(value);
		}
	}

	/**
	 * A specialized <code>BeanFactory</code> implementation that handles
	 * configuration declarations. This class will retrieve the correct
	 * configuration provider and delegate the task of creating the
	 * configuration to this object.
	 */
	static class ConfigurationBeanFactory implements BeanFactory {
		/**
		 * Creates an instance of a bean class. This implementation expects that
		 * the passed in bean declaration is a declaration for a configuration.
		 * It will determine the responsible configuration provider and delegate
		 * the call to this instance. If creation of the configuration fails and
		 * the <code>optional</code> attribute is set, the exception will be
		 * ignored and <b>null</b> will be returned.
		 * 
		 * @param beanClass
		 *            the bean class (will be ignored)
		 * @param data
		 *            the declaration
		 * @param param
		 *            an additional parameter (will be ignored)
		 * @return the newly created configuration
		 * @throws Exception
		 *             if an error occurs
		 */
		public Object createBean(Class beanClass, BeanDeclaration data,
				Object param) throws Exception {
			ConfigurationDeclaration decl = (ConfigurationDeclaration) data;
			String tagName = decl.getNode().getName();
			ConfigurationProvider provider = decl.getConfigurationBuilder()
					.providerForTag(tagName);
			if (provider == null) {
				throw new ConfigurationRuntimeException(
						"No ConfigurationProvider registered for tag "
								+ tagName);
			}

			try {
				return provider.getConfiguration(decl);
			} catch (Exception ex) {
				// If this is an optional configuration, ignore the exception
				if (!decl.isOptional()) {
					throw ex;
				} else {
					return null;
				}
			}
		}

		/**
		 * Returns the default class for this bean factory.
		 * 
		 * @return the default class
		 */
		public Class getDefaultBeanClass() {
			// Here some valid class must be returned, otherwise BeanHelper
			// will complain that the bean's class cannot be determined
			return org.apache.commons.configuration.Configuration.class;
		}
	}

	/**
	 * A specialized provider implementation that deals with file based
	 * configurations. Ensures that the base path is correctly set and that the
	 * load() method gets called.
	 */
	public static class FileConfigurationProvider extends ConfigurationProvider {
		/**
		 * Creates a new instance of <code>FileConfigurationProvider</code>.
		 */
		public FileConfigurationProvider() {
			super();
		}

		/**
		 * Creates a new instance of <code>FileConfigurationProvider</code> and
		 * sets the configuration class.
		 * 
		 * @param configClass
		 *            the class for the configurations to be created
		 */
		public FileConfigurationProvider(Class configClass) {
			super(configClass);
		}

		/**
		 * Creates the configuration. After that <code>load()</code> will be
		 * called. If this configuration is marked as optional, exceptions will
		 * be ignored.
		 * 
		 * @param decl
		 *            the declaration
		 * @return the new configuration
		 * @throws Exception
		 *             if an error occurs
		 */
		public AbstractConfiguration getConfiguration(
				ConfigurationDeclaration decl) throws Exception {
			FileConfiguration config = (FileConfiguration) super
					.getConfiguration(decl);
			config.load();
			return (AbstractConfiguration) config;
		}

		/**
		 * Initializes the bean instance. Ensures that the file configuration's
		 * base path will be initialized with the base path of the factory so
		 * that relative path names can be correctly resolved.
		 * 
		 * @param bean
		 *            the bean to be initialized
		 * @param data
		 *            the declaration
		 * @throws Exception
		 *             if an error occurs
		 */
		protected void initBeanInstance(Object bean, BeanDeclaration data)
				throws Exception {
			FileConfiguration config = (FileConfiguration) bean;
			config.setBasePath(((ConfigurationDeclaration) data)
					.getConfigurationBuilder().getConfigurationBasePath());
			super.initBeanInstance(bean, data);
		}
	}

	/**
	 * A specialized configuration provider for file based configurations that
	 * can handle configuration sources whose concrete type depends on the
	 * extension of the file to be loaded. One example is the
	 * <code>properties</code> tag: if the file ends with ".xml" a
	 * XMLPropertiesConfiguration object must be created, otherwise a
	 * PropertiesConfiguration object.
	 */
	static class FileExtensionConfigurationProvider extends
			FileConfigurationProvider {
		/** Stores the class to be created when the file extension matches. */
		private Class matchingClass;

		/**
		 * Stores the class to be created when the file extension does not
		 * match.
		 */
		private Class defaultClass;

		/** Stores the file extension to be checked against. */
		private String fileExtension;

		/**
		 * Creates a new instance of
		 * <code>FileExtensionConfigurationProvider</code> and initializes it.
		 * 
		 * @param matchingClass
		 *            the class to be created when the file extension matches
		 * @param defaultClass
		 *            the class to be created when the file extension does not
		 *            match
		 * @param extension
		 *            the file extension to be checked agains
		 */
		public FileExtensionConfigurationProvider(Class matchingClass,
				Class defaultClass, String extension) {
			this.matchingClass = matchingClass;
			this.defaultClass = defaultClass;
			fileExtension = extension;
		}

		/**
		 * Creates the configuration object. The class is determined by the file
		 * name's extension.
		 * 
		 * @param beanClass
		 *            the class
		 * @param data
		 *            the bean declaration
		 * @return the new bean
		 * @throws Exception
		 *             if an error occurs
		 */
		protected Object createBeanInstance(Class beanClass,
				BeanDeclaration data) throws Exception {
			String fileName = ((ConfigurationDeclaration) data)
					.getConfiguration().getString(ATTR_FILENAME);
			if (fileName != null
					&& fileName.toLowerCase().trim().endsWith(fileExtension)) {
				return super.createBeanInstance(matchingClass, data);
			} else {
				return super.createBeanInstance(defaultClass, data);
			}
		}
	}

	/**
	 * A specialized configuration provider class that allows to include other
	 * configuration definition files.
	 */
	static class ConfigurationBuilderProvider extends ConfigurationProvider {
		/**
		 * Creates a new instance of <code>ConfigurationBuilderProvider</code>.
		 */
		public ConfigurationBuilderProvider() {
			super(CustomConfigurationBuilder.class);
		}

		/**
		 * Creates the configuration. First creates a configuration builder
		 * object. Then returns the configuration created by this builder.
		 * 
		 * @param decl
		 *            the configuration declaration
		 * @return the configuration
		 * @exception Exception
		 *                if an error occurs
		 */
		public AbstractConfiguration getConfiguration(
				ConfigurationDeclaration decl) throws Exception {
			CustomConfigurationBuilder builder = (CustomConfigurationBuilder) super
					.getConfiguration(decl);
			return builder.getConfiguration(true);
		}
	}

	static {
		// register the configuration bean factory
		BeanHelper.registerBeanFactory(CONFIG_BEAN_FACTORY_NAME,
				new ConfigurationBeanFactory());
	}
}

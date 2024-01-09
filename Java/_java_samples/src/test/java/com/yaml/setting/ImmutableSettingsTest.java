package com.yaml.setting;

import com.yaml.setting.loader.SettingsLoader;
import com.yaml.setting.loader.SettingsLoaderFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SettingsLoaderFactory.class, Long.class})
public class ImmutableSettingsTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testLoadConfigAndGetOperation() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        Assert.assertEquals("testValue", settings.get("module01.testStringKey"));
        Settings subSettings = settings.getByPrefix("module01.");
        Assert.assertNotNull(subSettings);
        Assert.assertEquals("testValue", subSettings.get("testStringKey"));
        Assert.assertFalse(subSettings.getAsBoolean("testBooleanKey"));
        Assert.assertFalse(subSettings.getAsBoolean("test", false));
        Assert.assertTrue(10 == subSettings.getAsInt("testIntKey"));
        Assert.assertTrue(subSettings.getAsFloat("testFloatKey") == 3.45f);
        Assert.assertTrue(subSettings.getAsDouble("testDoubleKey") == 4.566);
        Assert.assertTrue(subSettings.getAsLong("testLongKey") == 3234242L);
    }

    @Test
    public void testGetDefaultValue() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        Assert.assertEquals("testStringDefaultValue", settings.get("nonDefaultedModule.testStringKey", "testStringDefaultValue"));
        Assert.assertTrue(settings.getAsInt("nonDefaultedModule.testIntKey", 1) == 1);
        Assert.assertTrue(settings.getAsLong("nonDefaultedModule.testLongKey", 1L) == 1L);
        Assert.assertTrue(settings.getAsDouble("nonDefaultedModule.testDoubleKey", 4.5222) == 4.5222);
        Assert.assertTrue(settings.getAsFloat("nonDefaultedModule.testFloatKey", 3.45f) == 3.45f);
    }

    @Test(expected = SettingsException.class)
    public void testExceptionWhenGet() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        settings.getAsInt("module01.testStringKey");
    }

    private URL loadConfigURL(String file) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = patternResolver.getResources(file);
            return resources[0].getURL();
        } catch (IOException e) {
            //just ignored.
        }
        return null;
    }

    @Test
    public void testPut() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        ImmutableSettings.DefaultSettingsBuilder defaultSettingsBuilder = new ImmutableSettings.DefaultSettingsBuilder().put(settings);
        Assert.assertNotNull(defaultSettingsBuilder);
    }

    @Test
    public void testExceptionFromLoadFromUrl() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        expectedEx.expect(SettingsException.class);
        try {
            builder.loadFromUrl(new URL("http://test.landy-china.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        SettingsLoader settingsLoader = mock(SettingsLoader.class);
        try {
            doThrow(new IOException("test")).when(settingsLoader.load(anyString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mockStatic(SettingsLoaderFactory.class);
        when(SettingsLoaderFactory.createSettingsLoader()).thenReturn(settingsLoader);
        builder.loadFromStream("test", new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
    }

    @Test
    public void testExceptionFromLoadFromStream() {
        expectedEx.expect(SettingsException.class);
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        SettingsLoader settingsLoader = mock(SettingsLoader.class);
        try {
            when(settingsLoader.load(anyString())).thenThrow(new IOException("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mockStatic(SettingsLoaderFactory.class);
        when(SettingsLoaderFactory.createSettingsLoader()).thenReturn(settingsLoader);
        builder.loadFromStream("test", mock(InputStream.class));
    }

    @Test
    public void testNumberFormateExceptionWhenGetAsLong() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        ImmutableSettings spySettings = spy(settings);

        when(spySettings.get(anyString())).thenReturn("test");
        expectedEx.expect(SettingsException.class);
        spySettings.getAsLong("test");
        spySettings.getAsDouble("test");
    }

    @Test
    public void testNumberFormateExceptionWhenGetAsDouble() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        ImmutableSettings spySettings = spy(settings);

        when(spySettings.get(anyString())).thenReturn("test");
        expectedEx.expect(SettingsException.class);
        spySettings.getAsDouble("test");
    }

    @Test
    public void testNumberFormateExceptionWhenGetAsFloat() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        ImmutableSettings spySettings = spy(settings);

        when(spySettings.get(anyString())).thenReturn("test");
        expectedEx.expect(SettingsException.class);
        spySettings.getAsFloat("test");
    }

    @Test
    public void testGetAsList() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        Assert.assertEquals(settings.getAsList("api.hmac").size(), 15);

        List<String> apiList = settings.getAsList("api.hmac");
        apiList.stream().forEach(System.out::println);

    }

    @Test
    public void testGetAsBoolean() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        ImmutableSettings spySettings = spy(settings);

        when(spySettings.get(anyString())).thenReturn("false", "0", "off", "on", "true");
        Assert.assertFalse(spySettings.getAsBoolean("test"));
        Assert.assertFalse(spySettings.getAsBoolean("test"));
        Assert.assertFalse(spySettings.getAsBoolean("test"));
        Assert.assertTrue(spySettings.getAsBoolean("test"));
        Assert.assertTrue(spySettings.getAsBoolean("test"));
    }

    @Test
    public void testGet() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        ImmutableSettings spySettings = spy(settings);

        Assert.assertEquals(spySettings.get("module01.testStringKey", ""), "testValue");
        Assert.assertEquals(spySettings.get("test", ""), "");

        System.out.println(spySettings.get("global.homepage.xhfplans"));
        System.out.println(spySettings.get("global.agent.images.host"));
        System.out.println(spySettings.get("local.profile.path"));
    }

    @Test
    public void testGetByPrefix() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        ImmutableSettings settings = (ImmutableSettings) builder.build();
        settings.getByPrefix("iWantThisPrefixIsLongestxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

    }

}

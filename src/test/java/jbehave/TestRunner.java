package jbehave;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.gherkin.GherkinStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * Created by ravindersingh on 7/9/17.
 */
public class TestRunner extends JUnitStories {

    @Override
    public Configuration configuration(){
        return new MostUsefulConfiguration().
                useStoryParser(new GherkinStoryParser()).
                useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE,Format.HTML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),new MyNumberTestSteps());
    }

    protected List<String> storyPaths() {
        String codeLocation = codeLocationFromClass(this.getClass()).getFile();
        return new StoryFinder().findPaths(codeLocation,asList("**/*.story"),asList(""));
    }
}

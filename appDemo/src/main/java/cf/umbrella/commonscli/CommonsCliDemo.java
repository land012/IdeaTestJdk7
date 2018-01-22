package cf.umbrella.commonscli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * create by xudazhou 2017/12/25
 */
public class CommonsCliDemo {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption(new Option("k1", "k1", true, "k1 desc"));
        options.addOption(new Option("k2", "k2", true, "k2 desc"));

        CommandLineParser cliParser = new DefaultParser();
        CommandLine cmd = cliParser.parse(options, args);
        System.out.println(cmd.getOptionValue("k1"));
        System.out.println(cmd.getOptionValue("k2"));
    }
}

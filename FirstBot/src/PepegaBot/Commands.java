package PepegaBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Locale;


public class Commands extends ListenerAdapter {

    public String prefix = "!"; //most common prefix is !

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(prefix + "test")) {
            //using discord embeds, create the object
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Test Title", "");
            //Links cant go here
            embed.setDescription("This is where descriptions go");
            embed.addField("Embed Field 1", "Content of Field", false);
            embed.addField("Embed Field 2", "Content of Field 2", false);
            embed.setColor(Color.CYAN);
            //To link user creator use <@! ID_HERE>
            embed.setFooter("This embed created by Jordan", event.getGuild().getOwner().getUser().getAvatarUrl());

            //Build a message using embed: build, then queue it
            /*To reply without quote
             event.getChannel().sendMessage(embed.build()).queue();*/

            //Reply with a reply to better notify  user
            event.getMessage().reply(embed.build()).queue();
            //Always clear once done
            embed.clear();
            // Basic test event.getMessage().reply("This worked").queue(); test cmd
        }

        if (args[0].equalsIgnoreCase(prefix + "giverole")) {
            if (event.getMessage().getMentionedRoles().toArray().length == 1) {
                if (event.getMessage().getMentionedUsers().toArray().length == 1) {
                    Member givenMember = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
                    Role roleToGive = event.getMessage().getMentionedRoles().get(0);
                    event.getGuild().addRoleToMember(givenMember, roleToGive).queue();
                    event.getMessage().reply("Role " + roleToGive.getAsMention() + "Has been given to " +
                            givenMember.getAsMention()).queue();
                    //This would be most useful for a Timeout/mute
                    new java.util.Timer().schedule(new java.util.TimerTask() {

                        @Override
                        public void run() {
                            event.getGuild().removeRoleFromMember(givenMember, roleToGive).queue();
                        }
                    }, 10000); //timeout duration in milliseconds
                } else {
                    event.getMessage().reply("Only one user at a time try again").queue();
                }
            } else {
                event.getMessage().reply("Role unable to be given").queue();
            }
        }
        if (args[0].equalsIgnoreCase(prefix + "removerole")) {
            if (event.getMessage().getMentionedRoles().toArray().length == 1) {
                if (event.getMessage().getMentionedUsers().toArray().length == 1) {
                    Member givenMember = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
                    Role roleToRemove = event.getMessage().getMentionedRoles().get(0);
                    event.getGuild().removeRoleFromMember(givenMember, roleToRemove).queue();
                    event.getMessage().reply("Role " + roleToRemove.getAsMention() + "Has been removed from " +
                            givenMember.getAsMention()).queue();
                } else {
                    event.getMessage().reply("Only one user at a time try again").queue();
                }
            } else {
                event.getMessage().reply("Role unable to be removed").queue();
            }
        }
    }

}

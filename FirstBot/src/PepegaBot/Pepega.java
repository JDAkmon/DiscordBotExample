package PepegaBot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Pepega {
    public static void main(String[] args) throws LoginException {
            JDABuilder javaDiscordApi = JDABuilder.createDefault("YourBotCodeGoesHere");
            javaDiscordApi.setActivity(Activity.playing("test!"));
            javaDiscordApi.setStatus(OnlineStatus.ONLINE);
            javaDiscordApi.addEventListeners(new Commands());
            javaDiscordApi.setChunkingFilter(ChunkingFilter.ALL); // Shows all users in server
        javaDiscordApi.setMemberCachePolicy(MemberCachePolicy.ALL); //Caching policy
        javaDiscordApi.enableIntents(GatewayIntent.GUILD_MEMBERS);
            javaDiscordApi.build();


//        JDA javaDiscordApi = JDABuilder.createDefault("").build();
//        javaDiscordApi.getPresence().setStatus(OnlineStatus.IDLE);
//        javaDiscordApi.getPresence().setActivity(Activity.playing("CSGO"));
//        javaDiscordApi.setGame

    }
}




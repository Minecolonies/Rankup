package com.minecolonies.rankup.util;

/**
 * Constants for our plugin.
 */
public final class Constants
{

    private Constants()
    {
        //Empty private constructor.
    }

    public static class PlayerInfo
    {
        private PlayerInfo()
        {
            //Empty private constructor.
        }

        public static final String PLAYER_NAME   = "{player}";
        public static final String PLAYER_GROUP  = "{group}";
        public static final String PLAYER_RANK   = "{rank}";
        public static final String PLAYER_PREFIX = "{prefix}";
        public static final String PLAYER_JOIN   = "{joindate}";
        public static final String PLAYER_LAST   = "{lastjoin}";
        public static final String PLAYER_TRACK  = "{track}";
    }

    public static class ModuleInfo
    {
        private ModuleInfo()
        {
            //Empty private constructor.
        }

        public static final String TIMING_TIME     = "{timing-time}";
        public static final String TIMING_NEXT     = "{timing-next}";
        public static final String ECONOMY_BAL     = "{economy-bal}";
        public static final String ECONOMY_NEXT    = "{economy-next}";
        public static final String MAGIBRIDGE_CURR = "{magibridge-currant}";
        public static final String MAGIBRIDGE_NEXT = "{magibridge-next}";
    }

    public static class SQL
    {
        private SQL()
        {
            //Empty private constructor.
        }

        public static final String WHERE  = "WHERE";
        public static final String FROM   = "FROM";
        public static final String SELECT = "SELECT";
        public static final String SET    = "SET";
        public static final String UPDATE = "UPDATE";

    }
}

package com.varchar.whatwatch.sqlite.contrat;

import android.provider.BaseColumns;


public class VideoMediaEntry  implements BaseColumns {

    public static final String TABLE_NAME ="video_media";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOCAL_IMAGE_ID = "local_image_id";
    public static final String IMAGE_URL = "image_url";
    public static final String DETAIL_IMAGE_URL = "detail_image_url";
    public static final String GENRE = "genre";
    public static final String DESCRIPTION = "description";
    public static final String RELEASE_DATE = "release_date";
    public static final String TYPE = "type";

}
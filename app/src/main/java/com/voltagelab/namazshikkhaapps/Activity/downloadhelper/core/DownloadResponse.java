package com.voltagelab.namazshikkhaapps.Activity.downloadhelper.core;

import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.domain.DownloadInfo;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.exception.DownloadException;

/**
 * Created by ixuea(http://a.ixuea.com/3) on 19/9/2021.
 */

public interface DownloadResponse {

    void onStatusChanged(DownloadInfo downloadInfo);

    void handleException(DownloadInfo downloadInfo, DownloadException exception);
}

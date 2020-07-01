/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.internal.session.typing

import im.vector.matrix.android.api.session.room.sender.SenderInfo
import im.vector.matrix.android.api.session.typing.TypingUsersTracker
import im.vector.matrix.android.internal.session.SessionScope
import javax.inject.Inject

@SessionScope
internal class DefaultTypingUsersTracker @Inject constructor() : TypingUsersTracker {

    private val typingUsers = mutableMapOf<String, List<SenderInfo>>()

    /**
     * Set all currently typing users for a room (excluding yourself)
     */
    fun setTypingUsersFromRoom(roomId: String, senderInfoList: List<SenderInfo>) {
        val hasNewValue = typingUsers[roomId] != senderInfoList
        if (hasNewValue) {
            typingUsers[roomId] = senderInfoList
        }
    }

    override fun getTypingUsers(roomId: String): List<SenderInfo> {
        return typingUsers[roomId] ?: emptyList()
    }
}

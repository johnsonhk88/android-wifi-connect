/**
 * Copyright 2011 Adrian Stabiszewski, as@nfctools.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nfctools.ndef.wkt.encoder;

import java.util.ArrayList;
import java.util.List;

import org.nfctools.ndef.NdefConstants;
import org.nfctools.ndef.NdefMessageEncoder;
import org.nfctools.ndef.NdefRecord;
import org.nfctools.ndef.wkt.records.Record;
import org.nfctools.ndef.wkt.records.SmartPosterRecord;

public class SmartPosterRecordEncoder implements RecordEncoder {

	@Override
	public boolean canEncode(Record record) {
		return record instanceof SmartPosterRecord;
	}

	@Override
	public NdefRecord encodeRecord(Record record, NdefMessageEncoder messageEncoder) {

		SmartPosterRecord myRecord = (SmartPosterRecord)record;

		List<Record> records = new ArrayList<Record>();

		if (myRecord.getTitle() != null)
			records.add(myRecord.getTitle());
		if (myRecord.getUri() != null)
			records.add(myRecord.getUri());
		if (myRecord.getAction() != null)
			records.add(myRecord.getAction());

		byte[] payload = messageEncoder.encode(records);

		return new NdefRecord(NdefConstants.TNF_WELL_KNOWN, SmartPosterRecord.TYPE, record.getId(), payload);
	}

}

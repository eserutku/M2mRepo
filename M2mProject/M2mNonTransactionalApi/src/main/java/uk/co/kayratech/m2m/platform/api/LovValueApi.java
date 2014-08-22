package uk.co.kayratech.m2m.platform.api;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueApi extends BaseApi<LovValue> {
	List<LovValue> getAllLovValues();
}

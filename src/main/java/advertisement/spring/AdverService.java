package advertisement.spring;

import java.util.List;

import advertisement.vo.AdverAppVo;
import advertisement.vo.AdverVo;

public interface AdverService {

	void makeAdApp(AdverAppVo adverAppVo);

	List<AdverVo> getcAd();

	List<AdverVo> getErAd();

	List<AdverVo> getEeAd();

	int getPoint(String userCode);

	void minusPoint(String userCode);



}

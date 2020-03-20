package chatMatching.spring;

import java.util.ArrayList;
import java.util.List;

import chatMatching.vo.ChatVo;
import chatMatching.vo.EmployerBoardVO;

public class ChatService {
	private ChatDao chatDao;
	
	
	public ChatService() {
		super();
	}

	public ChatService(ChatDao chatDao){
		this.chatDao = chatDao;
	}
	
	//채팅전송서비스
	public ChatVo sendChat(String fromCode, String toCode, String chatContent){
		chatDao.insertChatOne2(fromCode,fromCode,toCode,chatContent);
		chatDao.insertChatOne2(toCode,fromCode,toCode,chatContent);
		ChatVo result = chatDao.selectChatOne2(fromCode,toCode);
		//if 문으로 오전, 오후 붙여주기
		return result;
	}
	
	//채팅창 들어온후 서비스
	public List<ChatVo> getOneChatList(String myuserCode, String toCode){
		System.out.println("getOneChatList");
		System.out.println(myuserCode +","+toCode);
		chatDao.updateReadCheck2(myuserCode, toCode);
		List<ChatVo> oneChatVoList = chatDao.selectOneChatList2(myuserCode, toCode);
		
		for(ChatVo e :  oneChatVoList) {
			System.out.println(e.getUserCode()+":" + e.getChatContent() +"채팅리스트" );
		}
		//if 문으로 오전, 오후 붙여주기
		return oneChatVoList;
	}
	
	//채팅목록 띄우기 서비스
	public List<ChatVo> getAllChatList(String myUserCode){
		List<ChatVo> resultChatVoList = new ArrayList<ChatVo>();
		
		List<ChatVo> chatVoList = chatDao.selectAllChatList2(myUserCode);
		
		for(int i = 0; i < chatVoList.size(); i++) {
			ChatVo x = chatVoList.get(i);
			System.out.println("x:" + x);
			for(int j = 0; j < chatVoList.size(); j++) {
				ChatVo y = chatVoList.get(j);
				System.out.println("y:" + y);
				if(x.getFromCode().equals(y.getToCode()) && x.getToCode().equals(y.getFromCode())) {
					if(x.getChatNum() < y.getChatNum()) {
						chatVoList.remove(x);
						i--;
						break;
					}else {
						chatVoList.remove(y);
						j--;
					}
				}
			}
		}
		
		for(ChatVo o : chatVoList) {
			String fromCode = "";
			if(myUserCode.equals(o.getFromCode())) {
				fromCode = o.getToCode();
			}else {
				fromCode = o.getFromCode();
			}
			int readCount = chatDao.selectReadCount2(myUserCode, fromCode);
			o.setReadCheck(readCount); //readCheck를 안읽은 채팅갯수로 변경
			resultChatVoList.add(o);
		}
		
		
		return resultChatVoList;
	}
	
	public void deleteChat(String fromCode, String toCode){
		chatDao.updateReadCheck2(fromCode, toCode);
		chatDao.deleteChat2(fromCode, toCode);
	}
	
	//임시 : 수현언니 사장님게시글 뽑아오는 서비스
	public List<EmployerBoardVO> selectEb(String writerCode) {
		return chatDao.selectEb(writerCode);
		
	}

	public void insertMatching(String myuserCode, String employeeCode, String ebCode,
			String groupID) {
		chatDao.insertMatching2(myuserCode,employeeCode,ebCode,groupID);
		
	}

	public String selectSmsCheck(String myuserCode) {
		String smsCheck = chatDao.selectSmsCheck2(myuserCode);
		return smsCheck;
	}
	
	/*일정추가용
	 * @Override public CalendarAddDto getOneErMatching(String ebCode) {
	 * matchingDao.selectOneErMatching2(ebCode);
	 * 
	 * }
	 */
	
	
	
}

/*  ---------------------------------------------------
localStorage 로 부터 토큰 추출 
---------------------------------------------------------  */
function getToken(){
	return localStorage.getItem("token");
}


/*  ---------------------------------------------------
로그인 영역을 정의하는 컴포넌트, 이 컴포넌트를 html에서 사용할때는 tag로 표현하여 사용함 
ex)  <login-view></login-view>
---------------------------------------------------------  */
const LoginView={
	//2단계로 실행 : 가상 DOM 영역 ,  html 에 실제적으로 적용되는 실제DOM의 이전 단계
	template:`
    	<span v-if="member">
    		<input type="hidden" id="member_idx" v-model="member.member_idx">
        	<a href="#" >{{member.nickname}}</a>                        		
        	<a href="javascript:logout()">logout</a> 
        </span>
        
    	<span v-else>
            <span>
            	<a href="/movieclient/member/loginform"><span class="icon_profile"></span></a>
            </span>
        </span>
	`,
	data(){ //1단계 :  Vue 인스턴스가 생성될 때 실행되어 초기 데이터 객체를 반환
		return {
			member:null 			
		}
	},
	mounted(){
		console.log("mounted!!");
		this.fetchMember();
	},
	methods:{
		fetchMember(){
			alert("fetchMember() 호출");
			//서버로부터 jwt  토큰을 요청
			//getMemberInfo();
		}
	}	
}


const loginApp = Vue.createApp({
	components:{
		LoginView
	}
});

loginApp.mount("#loginApp");


/*  ---------------------------------------------------
클라이언트가 localStorage에 보유한 토큰을 서버로 보내면, 서버에서 토큰의 유효성 여부에 대한 판단을 하여 
만일 유효한 토큰이면 member json을 전송해주고, 아니면 에러 응답 정보를 보낸다
---------------------------------------------------------  */
function getMemberInfo(){
	$.ajax({
		url:"/movieapp/rest/member/logincheck",
		type:"GET",
		dataType:"json", //서버로부터 받을 데이터 형식이 json임을 서버에게 알린다 
		headers:{
			"Authorization" :"Bearer "+getToken()
		},
		success:function(result, status, xhr){
			console.log(result);
		},
		error:function(xhr, status, err){
			console.log("회원정보가 없습니다");
		}
		
	});
}






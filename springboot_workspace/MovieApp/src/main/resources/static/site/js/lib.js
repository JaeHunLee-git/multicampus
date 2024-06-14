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
        	<a href="#">{{member.nickname}}</a>                        		
        	<a href="javascript:logout()">logout</a> 
        </span>
        
    	<span v-else>
            <span>
            	<a href="/movieapp/member/loginform"><span class="icon_profile"></span></a>
            </span>
        </span>
	`,
	data(){ //1단계 :  Vue 인스턴스가 생성될 때 실행되어 초기 데이터 객체를 반환
		return {
			member:null 			
		}
	},
	mounted(){ //3단계
		//alert("mounted!!");
		this.fetchMember();
	},
	methods:{
		fetchMember(){
			//서버로부터 jwt  토큰을 요청
			getMemberInfo()
			.then(result =>{
				this.member=result;
				//alert("vue의 member 변수값은 "+this.member);
			})
			.fail( err => {
				console.log(err);
			});			
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
	
	//$.ajax()함수의 수행 후, 원칙적으로 Promise 객체가 반환된다.. 
	//이 Promise 객체의 then() 을 이용하면, 비동기방식으로 동작하는 로직이 완료되는 시점에 , 원하는 처리를 할 수 있다..즉 동기방식의
	//처리가 가능..
	return $.ajax({
		url:"/movieapp/rest/member/logincheck",
		type:"GET",
		dataType:"json", //서버로부터 받을 데이터 형식이 json임을 서버에게 알린다 
		headers:{
			"Authorization" :"Bearer "+getToken()
		}		
	})
	//then()   ajax() 에 의해 비동기방식으로 실행된 코드가 수행완료된 후 , 처리하고싶은 코드를 작성하는 메서드 영역
	.then(
		function(result, status, xhr){
			return result;
		},
		function(xhr, status, err){
			throw new Error(err);
		}
	);
}


/*---------------------------------------------------
페이징 처리 객체
---------------------------------------------------------  */
class Pager{
	constructor(totalRecord, pageSize, currentPage){
		this.totalRecord=totalRecord; //총 레코드 수
		this.pageSize = pageSize; //페이지당 보여질 레코드 수   skip().limit(여기에써먹을꺼임)  
		this.totalPage = Math.ceil(this.totalRecord/this.pageSize);
		this.blockSize=10; //블럭당 보여질 페이지 수 
		this.currentPage= currentPage; //현재 페이지 
		this.firstPage= this.currentPage - ((this.currentPage-1)%this.blockSize);
		this.lastPage=this.firstPage + (this.blockSize-1);
		this.curPos=(this.currentPage-1)*this.pageSize;  //skip(curPos).limit(pageSize)  
		this.num=this.totalRecord-this.curPos;//페이지당 시작 게시물 번호
	}
}


















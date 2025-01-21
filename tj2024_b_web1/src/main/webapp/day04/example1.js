console.log(`example1.js 실행`);

// 1. JS 자료
console.log(3);										//Number	정수
console.log(3.14);									//Number	실수
console.log(true);									//Boolean	논리
console.log(null);									//null		객체없다
console.log(undefined)								//undefined	정의없다
console.log("안녕1")								//String 	" "
console.log('안녕2')								//String 	' '
console.log(`안녕3`)								//String 	` `
console.log([3,3.14,true,`안녕4`])					//Array		배열[]
console.log(function 함수명(){})					//function	함수
console.log({"속성명1" : 3 , "속성명" : `안녕5`});	// object {}

// * let 변수 선언 키워드 , const 상수 선언 키워드

//2. JS 함수
// (1) 선언적 함수 : function 함수명( 매개변수명 , 매개변수명){실행문;}
function func1(a , b){ // 함수 정의 / 만들기
	let c = a + b;
	return c;
}
func1(3 ,4);		//함수 호출

// (2) 익명 함수 : function(매개변수명 , 매개변수명){실행문;}
const func2 = function(a,b){ // 함수 정의 / 만들기
	let c = a + b;
	return c;
}
func2(3,4);			//함수 호출

// (3) 람다식 (화살표) 함수  :( 매개변수명 , 매개변수명 ) => {실행문;}
const func3 = (a,b) => { c = a + b; return c;} // 함수 정의 / 만들기
func3(3,4);			//함수 호출

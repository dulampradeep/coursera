			var t1=0;var t2=0;var t=0;
			var ta="";
			var p="";
			var an="";
			var num="";
			var rd=0;
			var pi=Math.PI;
			if(!t1){
				document.form.radian.style.backgroundColor = "rgba(255,250,155,.7)";
			}
			function conrd(){
				t1=1;
				document.form.degree.style.backgroundColor ="rgba(255,250,155,.7)";
				document.form.radian.style.backgroundColor = "rgba(200,250,255,.2)";
			}
			function condr(){
				t1=0;
				document.form.radian.style.backgroundColor = "rgba(255,250,155,.7)";
				document.form.degree.style.backgroundColor = "rgba(200,250,255,.2)";
			}
			function intial(){
			if(t){
			if(t1){
				if(!rd){
					document.form.degree.style.backgroundColor ="rgba(255,250,155,.7)";
					document.form.radian.style.backgroundColor = "rgba(200,250,255,.2)";
					num=((22/7)/180)*num;rd=1;
					
					}
				}
			if(!t1){
				if(rd){
					document.form.radian.style.backgroundColor = "rgba(255,250,155,.7)";
					document.form.degree.style.backgroundColor = "rgba(200,250,255,.2)";
					num=(180/pi)*num;rd=0;
				}
			}}
		
			}
			function back() {
				
				if(t){
						if(num!=""){
							num=num.substring(0,num.length-1);
							ta=ta.substring(0,ta.length-1);
						}
						else{
							ta=ta.substring(0,ta.length-4);
							t=0;
						}
					}
					else{
						ta=ta.substring(0,ta.length-1);
						p=p.substring(0,p.length-1);
					}
					document.form.tdt.value=ta;
				return;
			}
			function funclear(){ta="";p="";num="";t=0;an=0;rd=0;}
				function funs(){
					
					t=1;
					ta=ta.concat("sin ");
					document.form.tdt.value = ta;
					return;
					
				}function func(){
				
					t=2;
					ta=ta.concat("cos ");
					document.form.tdt.value = ta;
					return;
					
				}function funt(){
				
					t=3;
					ta=ta.concat("tan ");
					document.form.tdt.value = ta;					
					return;
				}function funco(){
				
					t=4;
					ta=ta.concat("cot ");
					document.form.tdt.value = ta;
					return;
				}function funcsc(){
				
					t=5;
					ta=ta.concat("cosec ");
					document.form.tdt.value = ta;
					return;
				}function funsec(){
				
					t=6;
					ta=ta.concat("sec ");
					document.form.tdt.value = ta;
					return;
				}
				function funsqrt(){
				
					t=7;
					ta=ta.concat("sqrt ");
					document.form.tdt.value = ta;
					return;
				}
				function fun(k){
					if(k == "="){
						if(t==1){
						intial();   
							p=p.concat("Math.sin(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==2){
						intial();
							p=p.concat("Math.cos(Number(num))");
							an=eval(p).toPrecision(9);
							
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==3){
						intial();
							p=p.concat("Math.tan(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==4){
						intial();
							p=p.concat("1/Math.tan(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==5){
						intial();
							p=p.concat("1/Math.sin(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==6){
						intial();
							p=p.concat("1/Math.cos(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==7){
						intial();
							p=p.concat("Math.sqrt(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else{
							an=Number(eval(p));
							document.form.tdt.value = an;
							p=an;
							ta=an;
						
						}
						
					}
					else if(k == "+" || k == "-" || k == "*" || k == "/" || k == "**" || k == "%"||k=="<"||k==">"){
						if(t==1){
						intial();
							p=p.concat("Math.sin(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==2){
						intial();
							p=p.concat("Math.cos(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==3){
						intial();
							p=p.concat("Math.tan(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==4){
						intial();
							p=p.concat("1/Math.tan(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==5){
						intial();
							p=p.concat("1/Math.sin(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==6){
						intial();
							p=p.concat("1/Math.cos(Number(num))");
							an=eval(p);
							ta=ta.concat(k);
							p=p.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else if(t==7){
						intial();
							p=p.concat("Math.sqrt(Number(num))");
							ta=eval(p);
							p=ta.concat(k);
							document.form.tdt.value = ta;
							num="";
							temp=0;
							return;
						}
						else{
							p=p.concat(k);
							ta=ta.concat(k);
							document.form.tdt.value = ta;
						}
					}
					else {
					if(t==1||t==2||t==3||t==4||t==5||t==6||t==7){
						if(k=="("||k==")"){
							ta=ta.concat(k);
							}
						else{
							num=num.concat(k);
						ta=ta.concat(k);
						}
						document.form.tdt.value = ta;
					}
					else{
						p=p.concat(k);
						ta=ta.concat(k);
						document.form.tdt.value = ta;
					}
				}return;
				}
				
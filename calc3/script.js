var t=0;
			var ta="";
			var p="";
			var an="";
			var num="";
			var rd=0;
			var pi=Math.PI;
			function condr(){
			if(t){
			if(!rd){
				
				num=(pi/180)*num;rd=1;
					
				}
				return;
			}}
			function conrd(){
			if(t){
			if(rd){
				num=(180/pi)*num;rd=0;
			
				}
				return;
			}}
			function back() {
			var a=document.form.tdt.value;
				document.form.tdt.value=a.substring(0,a.length-1);
				//p=p.substring(0,a.length-1);
				ta=ta.substring(0,a.length-1);
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
							p=p.concat("Math.sin(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==2){
							p=p.concat("Math.cos(Number(num))");
							an=eval(p).toPrecision(9);
							
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==3){
							p=p.concat("Math.tan(Number(num))");
							an=eval(p).toPrecision(5);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==4){
							p=p.concat("1/Math.tan(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==5){
							p=p.concat("1/Math.sin(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
					
						}
						else if(t==6){
							p=p.concat("1/Math.cos(Number(num))");
							an=eval(p).toPrecision(9);
							document.form.tdt.value = an;
							num="";
							t=0;
							ta=an;
							p=an;
						
						}
						else if(t==7){
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
						num=num.concat(k);
						ta=ta.concat(k);
						document.form.tdt.value = ta;
					}
					else{
						p=p.concat(k);
						
						ta=ta.concat(k);
						document.form.tdt.value = ta;
					}
				}return;
				}
			
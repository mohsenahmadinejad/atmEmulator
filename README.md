<ul>
<li>This project is a simple implementation of a simple atm-emulator system.</li>
</ul>
<p>&nbsp;&nbsp;</p>
<ul>
<li><strong>Technologies in using :</strong></li>
<ul>
<li>java 11</li>
<li>spring boot</li>
<li>maven</li>
</ul>
<li><strong>Database :</strong></li>
<ul>
<li>MySQL</li>
</ul>
<li><strong>Libraries :</strong></li>
<ul>
<li><strong>lombok</strong>: for reduce boilerplate code for model/data objects.</li>
<li><strong>Swagger:&nbsp;</strong>for helps users build, document, test and consume RESTful web services<strong>.</strong></li>
<li><strong>ModelMapper</strong>: To make object mapping easy, by automatically determining how one object model maps to another, based on conventions.</li>
<li><strong>Resilience4j: </strong>A lightweight fault tolerance library.</li>
</ul>
</ul>
<p>&nbsp;</p>
<p>This system designs modular consists of tree modules and two micro services:</p>
<ul>
<li><strong>Bank-server</strong>: This microservice implements the requirements off bank-server of an atm-system.</li>
<li><strong>Atm-server</strong>: This microservice is for running on ATM device and contacts with bank-server</li>
<li><strong>Common dto</strong> : This is a module that contains the common dto between bank-server and atm-server</li>
</ul>
<ul>
<li><strong>Swagger&nbsp;</strong></li>
</ul>
<p>After running the app the best entry point is&nbsp;<strong>Swagger&nbsp;</strong>and the url are</p>
<p>&nbsp;<a href="http://localhost:8081/swagger-ui.html">http://localhost:8081/swagger-ui.html</a> for bank-server</p>
<p>&nbsp;<u><a href="http://localhost:8082/swagger-ui.html">http://localhost:8082/swagger-ui.html</a></u> for atm-server</p>
<p>You can see the APIs and you can test them.</p>
<p>&nbsp;</p>
<ul>
<li><strong>Security</strong></li>
</ul>
<p><strong>SignIn : </strong></p>
<ul>
<li>In both bank-server and atm-server there is <a href="http://localhost:8082/swagger-ui.html#/operations/authentication-controller/generateTokenUsingPOST"><strong>/signIn</strong></a> api</li>
<li>You can set preferred Authentication Method(pin/finger print) in <a href="http://localhost:8082/swagger-ui.html#/operations/authentication-controller/setPreferredAuthenticationMethodUsingPUT"><strong>/preferred-authentication-method</strong></a> api</li>
</ul>
<ul>
<ul>
<li>The singIn, the default card number is &ldquo;200&rdquo; and the default password is &ldquo;123&rdquo; from PIN method and &ldquo;abc&rdquo; from FINGER_PRINT method</li>
<li>The default Authentication Method is PIN</li>
</ul>
</ul>
<p><strong>Disabling/Enabling security:</strong></p>
<ul>
<li>Go to properties file</li>
<li>Look for property activation.status</li>
<li>Set it to on/off</li>
</ul>
<p><strong>&nbsp;</strong></p>
<p><strong>&nbsp;</strong></p>
<p><strong>How to use with security?</strong></p>
<ol>
<li>go to atm-server: &nbsp;<u><a href="http://localhost:8082/swagger-ui.html">http://localhost:8082/swagger-ui.html</a> </u></li>
<li>go to <a href="http://localhost:8082/swagger-ui.html#/operations/authentication-controller/generateTokenUsingPOST"><strong>/signIn</strong></a> api and signIn</li>
<li>The retened jwt token set to session</li>
<li>Call other APIs</li>
<li></li>
</ol>
<p>&nbsp;</p>
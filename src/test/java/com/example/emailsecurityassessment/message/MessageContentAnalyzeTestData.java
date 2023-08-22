package com.example.emailsecurityassessment.message;

class MessageContentAnalyzeTestData {
    static final String EXAMPLE_HTML = """
            <div dir="ltr"><a href="https://exampleWebsite.com/">https://exampleWebsite.com/</a><br></div><div id="DAB4FAD8-2DD7-40BB-A1B8-4E2AA1F9FDF2"><br><table style="border-top:1px solid #d3d4de"><tr><td style="width:55px;padding-top:13px"><a href="https://www.avast.com/sig-email?utm_medium=email&amp;utm_source=link&amp;utm_campaign=sig-email&amp;utm_content=webmail" target="_blank"><img src="https://s-install.avcdn.net/ipm/preview/icons/icon-envelope-tick-round-orange-animated-no-repeat-v1.gif" alt="" width="46" height="29" style="width: 46px; height: 29px;"></a></td><td style="width:470px;padding-top:12px;color:#41424e;font-size:13px;font-family:Arial,Helvetica,sans-serif;line-height:18px">Nie zawiera wirusów.<a href="https://www.avast.com/sig-email?utm_medium=email&amp;utm_source=link&amp;utm_campaign=sig-email&amp;utm_content=webmail" target="_blank" style="color:#4453ea">www.avast.com</a></td></tr></table><a href="#DAB4FAD8-2DD7-40BB-A1B8-4E2AA1F9FDF2" width="1" height="1"></a></div>
            """;

    static final String EXAMPLE_PLAIN = """
            https://exampleWebsite.com/
                       
            <https://www.avast.com/sig-email?utm_medium=email&utm_source=link&utm_campaign=sig-email&utm_content=webmail>
            Nie
            zawiera wirusów.www.avast.com
            <https://www.avast.com/sig-email?utm_medium=email&utm_source=link&utm_campaign=sig-email&utm_content=webmail>
            <#DAB4FAD8-2DD7-40BB-A1B8-4E2AA1F9FDF2>
            """;

    static final String XKOM_NEWSLETTER_HTML = """
            <div dir="ltr"><br><br><div class="gmail_quote"><div dir="ltr" class="gmail_attr">---------- Forwarded message ---------<br>From: <strong class="gmail_sendername" dir="auto">x-kom</strong> <span dir="auto">&lt;<a href="mailto:mailing@mail.x-kom.pl">mailing@mail.x-kom.pl</a>&gt;</span><br>Date: Mon, 21 Aug 2023 at 07:05<br>Subject: 💻 Laptopy do 1400 zł taniej<br>To:  &lt;<a href="mailto:senderEmail@gmail.com">senderEmail@gmail.com</a>&gt;<br></div><br><br><div class="msg2707632969868879294"><u></u>
                       
                       
            \s
             \s
             \s
             \s
            \s
                       
                       
            <div width="100%" bgcolor="#ffffff">
            <div style="font-size:1px;display:none!important">Nie tylko do nauki &gt;&gt;</div>
             <table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="100%">
              <tbody>
               <tr>
                <td>
                  <div style="display:none;max-height:0px;overflow:hidden;font-size:0pt">
            </div>
                    <table class="m_2707632969868879294mobile-hidden" width="100%" cellspacing="0" cellpadding="0" align="center" border="0">
                      <tbody>
                        <tr>
                          <td align="center" style="padding:10px 0px">
                            <a class="m_2707632969868879294mobile-hidden" href="https://view.mail.x-kom.pl/?qs=10260dfaaa7d1119e4f3a47a58669177963276cbc7c7ef0f0cd0fab6c84b2aafd7042a9eddb3380e283d3a431c18f23777bf7a0d550603fa4ba75281572d044b882e8a82e285bfba376891355305fa1b7d203df7e9a2eed8" target="_blank"><span style="color:#949494;text-decoration:none;text-align:center;font-size:12px">zobacz w przeglądarce »</span></a>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                \s
                \s
               \s
               \s
                 <table align="center" bgcolor="#fff" border="0" cellpadding="0" cellspacing="0" style="background-color:#ffffff" width="600" class="m_2707632969868879294size-600">
                  <tbody>
                   <tr>
                    <td>
                    \s
                       
                    \s
                    \s
                     <table class="m_2707632969868879294size-600" width="600" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#ffffff">
                      <tbody><tr>
                       <td><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="background:#3971d9!important">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#3971d9" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-pdd-30-40-30-40">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294headerLogo">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-md-3">
            <a href="https://click.mail.x-kom.pl/?qs=a272951347045344471de7f25f87f1e9169ed379a8a684b3473a85067d11fa50e7cc57f23c3b548843d6e96788abcdb69753e90824b08a0228e64370fd41df32" target="_blank">
            <img src="https://image.mail.x-kom.pl/lib/fe3d15707564057c7d1370/m/4/645daaf0-3db8-46b6-96dc-2c9cfd806b34.png" title="logo x-kom" width="96">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294span-c2">
            <span class="m_2707632969868879294f-size-21">
            oczywiście, że do nauki
            </span>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294span-c1">
            <span class="m_2707632969868879294f-size-30">
            <b>
            laptopy taniej nawet <span style="white-space:nowrap">o 1400 zł</span>
            </b>
            </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
              <tr>
            <td class="m_2707632969868879294headerCode">
            <table cellspacing="0" cellpadding="0" border="0" align="left">
            <tbody><tr>
            <td style="padding-left:0px">
            <span style="font-size:15px;color:#ffffff;font-family:Arial">
            użyj kodu:
            </span>
            </td>
            <td style="padding-left:10px">
            <span class="m_2707632969868879294f-size-22" style="text-align:left;color:#ffccff;font-family:Arial;font-weight:bold">bts-laptopy</span>
            </td>
            <td class="m_2707632969868879294mobile-hidden" style="padding-left:5px">
            <img src="https://image.mail.x-kom.pl/lib/fe3d15707564057c7d1370/m/4/5156b020-71ee-4970-b612-8feef791c1d3.png" title="icon-promocja">
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294headerButton">
            <table width="170" cellspacing="0" cellpadding="0" border="0" align="left">
            <tbody><tr>
            <td class="m_2707632969868879294f-size-19" style="width:170px;height:44px;padding:0px 0px;background-color:#ffffff;border-radius:30px" width="170" height="44" bgcolor="#ffffff" align="center">
            <a height="44" href="https://click.mail.x-kom.pl/?qs=a27295134704534428d58d85f4acb5c983d13b6d0d4232edb5459ba75473378fb24d2e66231b6d2b1406e81c931a6d2eddd27a5e13a284fc0336954fd069629a" style="font-family:Arial!important;font-family:sans-serif;color:#000000;text-align:center;text-decoration:none;font-size:19px;display:block;background-color:#ffffff;padding:8px 10px;border-radius:30px;white-space:nowrap" title="Sprawdź" target="_blank">skorzystaj</a></td></tr></tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <table role="presentation" width="100%" cellspacing="0" cellpadding="0">
            <tbody><tr>
            <td align="center">
            <a href="https://click.mail.x-kom.pl/?qs=a272951347045344535fdaabedd1826e91ca9b60ae7469ef50af3625d0f0bc133428850b5af7e2a85114f04ede3c50d744b75cae2877290d641492ca37055b62" title="" target="_blank">
            <img src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/d20e4b41-b47f-4194-9376-6b1dfa9d0a0d.jpg" alt="top-1-1" style="display:block;padding:0px;text-align:center;height:auto;width:100%;border:0px none transparent" width="600">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <table role="presentation" width="100%" cellspacing="0" cellpadding="0">
            <tbody><tr>
            <td style="vertical-align:top" align="center">
            <a href="https://click.mail.x-kom.pl/?qs=a2729513470453445f8876efa3b37bf3e28e863cf9adcad4116df997d5c5679072eecf336b4c561b8c751f6e57e9a3c067c8ccc537d104cdd7e14dd3895f999f" title="złap okazje" target="_blank">
            <img src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/563903c0-075f-46ea-b29e-7a4efd551ff1.png" alt="" style="display:block;padding:0px;text-align:center;height:auto;width:100%;vertical-align:top;border:0px none transparent" width="600">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="background-color:transparent;min-width:100%"><tbody><tr><td style="padding:0px"><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
              <tbody><tr><td height="20"></td></tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
              <tbody><tr>
                <td height="10"></td></tr>
            <tr>
            <td class="m_2707632969868879294col-pdd-0-12-30-12">
            <span class="m_2707632969868879294f-size-26" style="color:#000000;font-weight:bold">
            przygotuj się <span style="white-space:nowrap">na powrót do szkoły</span>
            </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="background-color:transparent;min-width:100%"><tbody><tr><td style="padding:0px"><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <table cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td width="600">
            <table width="96%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344d3145952cdea04f35a66b8ed0bef840f159fce0be7da2bc6a6ef8b77f8c744b32fb6b1538d45d6dc6d9a541a6ed1870fbb2dcdd38b8433f0" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/48b7285e-281a-4973-827a-30aaef23c254.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453446ae479459f0861bcec8bdf53200329ed5e50f67426492046495c0e99c0087572fecc78f137392fd040499991f35b4b55b0085daad0afd9a7" target="_blank">
              <span class="m_2707632969868879294f-size-15" style="color:#646464">laptop 14”</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453449253288f65dd4cfca0a090e2c7d8fffa35093055b162173fcb512cdbad44ec26dc3d7f46db31cb87b89580432250c46f77ece30c574bca7a" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">ASUS ZenBook 14 UM425QA R5-5600H/16GB/ 512/Win11
            </span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344281b6928c99cbf7b972324770a64ca948672346c79ddc168bff83e38ada20f0e97444db83b4356f60ef4fe578f76b348ca2635a792dfcf69" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>3999 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">3599 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
             \s
                       
                       
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453445015ceb351faa15f7b8c5fad94f9669e6f4c275201408d23cc985fe3cf38fe83250497f905aa2e1f10fe9814794e48feae9d2fa94916e029" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/ace8cc6f-b9e4-4ab0-bdc7-50fcd689877e.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344cac5ec5619ef115c3ebd407d6c112ce2f29a698908c24483ebd61b06659c46ea8328317962b25895cceca755e35276821835f3510887f69e" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">laptop 16”</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344e97d53f2064660ab7bd19932efe09c4ca55d5f1ee9781b72950473505a2b002ceedfa2fd724894ed873da1d406dab2081f447fe915f8922d" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">Dell Inspiron 5625 Ryzen 5 5625U/16GB/ 512/Win11
            </span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a27295134704534447183dc04f62a16c903424abd105c0c7cb065356e1c0d43c7b6bd2d05e04f3d44418bb9d4935532fa515e782b3e55379c5fa7b47342e7044" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>3999 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">2999 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
             \s
             \s
                       
            <td class="m_2707632969868879294col-prod-1-mobile" width="160">
            <table width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344234c2ca203fa6c18cb6b688ca1c24e46869670164d1f0f05d0160e7ec8a591b12d3eee74b7e71910d5e333f71a468156ac44b192fc90522d" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/2a8dea48-c34e-432f-a898-522a3b778d8f.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344a355ccc076347a973eefd9d8b33e470cbf33ab2c9bf1df82f25d9eaead3cd798841472836ce613cae9c1c48c6e51889ed25632accda3f774" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">laptop 17,3”</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a27295134704534485fff67839f116dc5d335c5e105995f0f951b6dbec1f441e7135c36c093710b024d30fa3ffb192c526237f7896f5b9582562df18a09ae46f" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">MSI GF76 i7-12650H/8GB/ 512 RTX3050 144Hz</span>
                       
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344eaf1fa566b9e6ab79f9c161202479f84897059f8358c80fd07c3690228e941d268eaf576ba3f1124b190cd705740d14a33874848c27791b3" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>3999 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">3599 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
                       
             \s
                       
            <tr>
            <td width="600">
            <table width="96%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344c4198d00f628de1e479bec6e24d408b60e2637127adc6f35f75d4f414c92a56534885a57add4a71ca570f42ad8a059f1dccb4d625a085002" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/31f3bf03-9cb9-441d-85ad-67f7eb62d48a.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453447b76dd32f22447c0ceb5e443934aa1628b3726fcd0e7522b9441fd754106d3cce9648fead7f650c12804ff1406f0ce4f77134f8988187919" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">mysz</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a27295134704534475943e06be6fa05e2e1a613f23444420a311c4a67ba174649865931d678e623ffef1cc41ceb64fcb55031af254754655f766fe140becbd2a" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">Razer Naga V2 Pro</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344a2f71a4f78e921fc4e8446c5c1dd81c081d5c69ad1d3988027cf9d3ef1dfc8134aa5799f85d9613ba6db2865d2d7ee82e3b65a69b566ee53" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>899 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">666 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            \s
            <tr>
            <td class="m_2707632969868879294desktop-hidden-line" width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
                       
            </tbody></table>
            </td>
             \s
             \s
                       
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453447c496355ee0204008025701d5c5978f2f926d6fd93d14a077f69b1ee302f752abe7f14efc97c64d1322eb8bc79b31ebb30b700949ff13e77" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/7e2f303e-984b-4414-ae8b-e6238aede6b5.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344a2886e77263ab231fa5b4ff6dbdb692f7bac635a31447411f749ee1d6ef94729be71cc907067df70afd93e5b797b52b36ddab310dafa750f" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">podstawka</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344b76e0d638a6b234cc3705a42357c8da7c780c88e2e90c63ba7c0b8d236e72ff4fa6f885d0fcd672a789dc7d59717a1503a8b829e96da1a54" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">Mozos LS3-ALU aluminiowa
            </span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453440a87caaa0d9e0fe9c7f688bef3fe8f6285c9c6655b739ceac442a77dc68db2d8f5eee21aae2a6490fb35056552e2b83748b8d2f988caae6b" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>139 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">95 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
             \s
            <tr>
            <td class="m_2707632969868879294desktop-hidden-line" width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
                       
            </tbody></table>
            </td>
             \s
             \s
                       
            <td class="m_2707632969868879294col-prod-1-mobile" width="160">
            <table width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344831846f7e20b7693913cdf0cd40321c15dda03abd9402a9a1434c41acd88247b8914eda385a14e3be0ad98479c875e13ed96948cfb52e61c" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/c6cbc8f8-6272-4693-a2ff-3060a8bb77df.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344ce909fa5985513bc171212c3d04e8b3955c3bec883ff271d70007ae4146f21f5e7865f1f49743981b075e5c26f0c9f985db0d520588593b6" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">antywirus</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344ed517942112324743e29b24f96bde723ef6c2c8aa754ca87be6c6940988f3c8594fc334b614458ddc0aadbf25526dcbfc554dd6fffcd475a" target="_blank">
              <span class="m_2707632969868879294f-size-17" style="color:#000000">NortonLifeLock Antivirus Plus 1st. (12m.)</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a27295134704534462dfbfda20556896bfdedf0eab32ebf2bdf264ba26658cc06dd00bdc22a63ce1702d7baab985bc9843ddef81360f8850db475227e755ac4b" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>69 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">19 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
                       
            <tr>
            <td class="m_2707632969868879294desktop-hidden-line" width="100%" align="center">
            <table width="100%" cellspacing="0" cellpadding="0" align="center">
            <tbody><tr>
            <td style="border-bottom:1px solid #e6e6e6">
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            <tr>
            <td>
            <span class="m_2707632969868879294f-size-14"> </span>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
                       
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
                       
                       
                       
                       
                       
            <table class="m_2707632969868879294desktop-hidden" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294desktop-hidden">
            <table class="m_2707632969868879294desktop-hidden" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td width="600">
            <table width="96%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453446fd72821d30f3529d3c66f800ad2dd98ff71fda94bb4e6e76447f301ab1b09768057f302263979e511455768835e3e704e7bcf73b5ef79a8" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/2a8dea48-c34e-432f-a898-522a3b778d8f.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344a355ccc076347a973eefd9d8b33e470cbf33ab2c9bf1df82f25d9eaead3cd798841472836ce613cae9c1c48c6e51889ed25632accda3f774" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">laptop 17,3”</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453442e9b6e903d2ee79cd7e8849099970c916dfccdafd1459b5db9093320ae02a44a85259cc8791b2c31aca27421de2c2cd2391c84af1952248c" target="_blank">
              <span class="m_2707632969868879294f-size-17" style="color:#000000">MSI GF76 i7-12650H/8GB/512 RTX3050 144Hz</span>
                       
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344eaf1fa566b9e6ab79f9c161202479f84897059f8358c80fd07c3690228e941d268eaf576ba3f1124b190cd705740d14a33874848c27791b3" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>3999 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">3599 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
             \s
             \s
                       
            <td class="m_2707632969868879294col-prod-1" width="160">
            <table class="m_2707632969868879294size-600" width="160" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-prod-img">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344d4054be9c48ca3123491301f8424ac2c1b7593771eb97c68f84bb6f79e6577121105311d61ddd8f2d27982e534e2fe281de5dbf1bfb3a65b" target="_blank">
            <img class="m_2707632969868879294size-600" style="border-radius:10px" src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/c6cbc8f8-6272-4693-a2ff-3060a8bb77df.jpg" width="160">
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-category">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a272951347045344ce909fa5985513bc171212c3d04e8b3955c3bec883ff271d70007ae4146f21f5e7865f1f49743981b075e5c26f0c9f985db0d520588593b6" target="_blank">
            <span class="m_2707632969868879294f-size-15" style="color:#646464">antywirus</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td class="m_2707632969868879294col-prod-name-90">
            <table class="m_2707632969868879294col-prod-name-90" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td style="vertical-align:top">
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a2729513470453443ae4b5a07d4d681ba73baafee88744c61f6f582ff792aa43eaa646d092270e0ec6a47c0df2fa15f54a3d5ab62c5d7012c709baf7020571d9" target="_blank">
            <span class="m_2707632969868879294f-size-17" style="color:#000000">NortonLifeLock Antivirus Plus 1st. (12m.)</span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td>
            <a title="złap okazje" href="https://click.mail.x-kom.pl/?qs=a27295134704534462dfbfda20556896bfdedf0eab32ebf2bdf264ba26658cc06dd00bdc22a63ce1702d7baab985bc9843ddef81360f8850db475227e755ac4b" target="_blank">
            <span><span class="m_2707632969868879294f-size-14" style="color:#646464"><s>69 zł</s></span>
              
            <span class="m_2707632969868879294f-size-18" style="color:#000000">19 zł</span></span>
            </a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
                       
            </td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td align="center">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294col-pdd-40-0-40-0" align="center">
            <table role="presentation" cellspacing="0" cellpadding="0" border="0">
            <tbody><tr>
            <td class="m_2707632969868879294f-size-19" style="border-radius:30px;width:260px;background-color:#ffffff;border:1px solid;border-color:#000000;height:42px" width="260" height="42" bgcolor="#FFFFFF" align="center">
            <a class="m_2707632969868879294f-size-19" height="38" href="https://click.mail.x-kom.pl/?qs=a272951347045344abb2f3baa9e0cc7e4cfa064c6e6d0a4aa9cbc8e7eb48209793447fdf36fcb6f340924d9161cc5c7ee5264316b7d602f4b5747a7c98d02d7d" style="font-family:Arial,sans-serif;color:#000000;text-align:center;text-decoration:none;display:block;background-color:#ffffff;padding:10px 10px;border-radius:30px;white-space:nowrap" title="Sprawdź" width="290" target="_blank">zobacz więcej</a></td></tr></tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table width="100%" cellspacing="0" cellpadding="0" align="center" border="0">
              <tbody><tr>
              <td class="m_2707632969868879294col-pdd-0-16-0-16">
                <table style="border-radius:10px" width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#f5f5f5" align="center">
                <tbody><tr>
                  <td class="m_2707632969868879294col-pdd-30-0-30-0 m_2707632969868879294m-pdd-left-15">
                    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                      <tbody><tr>
                        <td width="200" align="center" class="m_2707632969868879294b3-display-block">
                          <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                            <tbody><tr>
                              <td class="m_2707632969868879294b3-display" align="center">
                                <img alt="darmowa dostawa" style="height:44px" src="https://image.mail.x-kom.pl/lib/fe3d15707564057c7d1370/m/5/a4a8696d-a148-4aed-afdb-876050d60a3f.png" height="44">
                              </td>
                             \s
                              <td align="center" class="m_2707632969868879294f-size-15 m_2707632969868879294b3-display m_2707632969868879294m-pdd-left-15">
                                <span style="color:#464646;line-height:18px">darmowa dostawa<br>
                                  w aplikacji od 99 zł</span>
                              </td>
                            </tr>
                          </tbody></table>
                        </td>
                        <td width="200" align="center" class="m_2707632969868879294b3-display-block m_2707632969868879294m-pdd-top-10">
                          <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                            <tbody><tr>
                              <td align="center" class="m_2707632969868879294b3-display m_2707632969868879294m-pdd-top-10">
                                <img style="height:44px" alt="zwroty paczkomat" src="https://image.mail.x-kom.pl/lib/fe3d15707564057c7d1370/m/5/6763e74b-44ae-4660-895b-b918b9164b8e.png" height="44">
                              </td>         \s
                             \s
                              <td align="center" class="m_2707632969868879294f-size-15 m_2707632969868879294b3-display m_2707632969868879294m-pdd-left-15 m_2707632969868879294m-pdd-top-10">
                                <span style="color:#464646;line-height:17px">bezpłatne zwroty<br>
                                  w Paczkomacie</span>
                              </td>
                            </tr>
                          </tbody></table>
                        </td>
                        <td width="200" align="center" class="m_2707632969868879294b3-display-block m_2707632969868879294m-pdd-top-10">
                          <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                            <tbody><tr>
                              <td align="center" class="m_2707632969868879294b3-display m_2707632969868879294m-pdd-top-10">
                                <img style="height:44px" alt="zwroty paczkomat" src="https://image.mail.x-kom.pl/lib/fe3d15707564057c7d1370/m/5/c145e710-2ed1-41f3-a6e7-3d2bb94105c4.png" height="44">
                              </td>         \s
                             \s
                              <td align="center" class="m_2707632969868879294f-size-15 m_2707632969868879294b3-display m_2707632969868879294m-pdd-left-15 m_2707632969868879294m-pdd-top-10">
                                <span style="color:#464646;line-height:17px">profesjonalne <br>doradztwo
                                  ekspertów</span>
                              </td>
                            </tr>
                          </tbody></table>
                        </td>
                      </tr>
                    </tbody></table>
                  </td>
                </tr>
              </tbody></table> \s
              </td>
              </tr>
            </tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
              <tbody><tr>
                <td>
                  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                    <tbody><tr>
                      <td class="m_2707632969868879294col-pdd-name-30-16-0-16">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                         \s
                            <tbody><tr>
                              <td class="m_2707632969868879294size-name-360" width="50%">
                                <table width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
                                 \s
                                    <tbody><tr>
                                      <td class="m_2707632969868879294section-group-title">
                                        <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
                                         \s
                                            <tbody><tr>
                                              <td class="m_2707632969868879294size-360" width="100%">
                                                <table width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
                                                 \s
                                                    <tbody><tr>
                                                      <td style="line-height:24px">
                                                        <span class="m_2707632969868879294f-size-19" style="color:#808080">
                                                          odkryj nasze aktualne okazje
                                                        </span>
                                                      </td>
                                                    </tr>
                                                 \s
                                                </tbody></table>
                                              </td>
                                            </tr>
                                         \s
                                        </tbody></table>
                                      </td>
                                    </tr>
                                 \s
                                </tbody></table>
                              </td>
                            </tr>
                         \s
                        </tbody></table>
                      </td>
                    </tr>
                  </tbody></table>
                </td>
              </tr>
            </tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294row-pdd-promo">
            <table style="display:inline-block;float:right" class="m_2707632969868879294section-promo" width="49%" cellspacing="0" cellpadding="0" border="0" align="right">
            <tbody><tr>
            <td align="right">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294section-promo-copy">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="right">
            <tbody><tr>
            <td class="m_2707632969868879294mobile-hidden" height="46"> </td> \s
            </tr>
            <tr><td>
            <span style="text-align:left;color:#000000;line-height:22px">
            <span class="m_2707632969868879294f-size-17">kup Galaxy Z Fold5 lub Z Flip5
            <br>
            <span class="m_2707632969868879294f-size-22" style="color:#000000;font-weight:bold;line-height:26px">
            i zyskaj do 3000 zł <br>
            oraz Galaxy Buds2 Pro
                       
                       
                       
            </span>
            </span></span></td>
            </tr>
            <tr>
            <td class="m_2707632969868879294section-promo-button">
            <table role="presentation" cellspacing="0" cellpadding="0" border="0">
            <tbody><tr>
            <td style="border-radius:30px;width:120px;border:1px solid #000000;height:35px" width="120" height="35" bgcolor="#FFFFFF" align="center">
            <a title="poznaj nasze aktualne okazje" height="35" href="https://click.mail.x-kom.pl/?qs=a272951347045344a92e01d880db7109ed0d4897e7ce34754ec9cd8f5cff851b9a041cc8c4aacfa831481eb50020f5e20196c8cc2be37caabc64f25cf0ee5387" style="font-family:Arial,sans-serif;color:#000000;text-align:center;text-decoration:none;display:block;font-size:16px;padding:8px 25px 9px;border-radius:30px" width="130" target="_blank">sprawdź</a>
            </td></tr><tr><td height="4"></td></tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            <table style="display:inline-block;float:left" class="m_2707632969868879294section-promo" width="40%" cellspacing="0" cellpadding="0" align="left">
            <tbody><tr>
            <td align="left">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
            <tbody><tr>
            <td class="m_2707632969868879294section-photo-left" align="left">
            <a title="poznaj nasze aktualne okazje" href="https://click.mail.x-kom.pl/?qs=9603a7c2195459f7cd2264341e46dfd2aa7fd1afb9c7bd93ace0a9c60d249a4bc2698d94e15e79d6cb9d4d1a1cba37f4f4f408c1f76ffcf3449f2f61ec4817f5" target="_blank"><img src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/cc3151f5-22e7-4e09-9fdb-69a065ef59f6.jpg" width="100%"></a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>\s
                       
            </tbody></table><table class="m_2707632969868879294desktop-hidden" width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr class="m_2707632969868879294size-600">
            <td class="m_2707632969868879294desktop-hidden" style="border-top:1px solid #d9d9d9" width="100%"> 
            </td>
            </tr>
            </tbody></table>
                       
            </td></tr></tbody></table></td></tr></tbody></table><table cellpadding="0" cellspacing="0" width="100%" role="presentation" style="min-width:100%"><tbody><tr><td><table class="m_2707632969868879294size-600" width="600" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294row-pdd-promo">
            <table style="display:inline-block;float:right" class="m_2707632969868879294section-promo" width="49%" cellspacing="0" cellpadding="0" border="0" align="right">
            <tbody><tr>
            <td align="right">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tbody><tr>
            <td class="m_2707632969868879294section-promo-copy">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="right">
            <tbody><tr>
            <td class="m_2707632969868879294mobile-hidden" height="50"> </td> \s
            </tr>
            <tr><td>
            <span style="text-align:left;color:#000000;line-height:25px">
            <span class="m_2707632969868879294f-size-17">laptopy Chromebook
            <br>
            <span class="m_2707632969868879294f-size-22" style="color:#000000;font-weight:bold;line-height:26px">
                       
            już od 899 zł
                       
            </span>
            </span></span></td>
            </tr>
            <tr>
            <td class="m_2707632969868879294section-promo-button">
            <table role="presentation" cellspacing="0" cellpadding="0" border="0">
            <tbody><tr>
            <td style="border-radius:30px;width:120px;border:1px solid #000000;height:35px" width="120" height="35" bgcolor="#FFFFFF" align="center">
            <a title="poznaj nasze aktualne okazje" height="35" href="https://click.mail.x-kom.pl/?qs=9603a7c2195459f7f268286731ad9e0598ad2f12eca06fff0d406659a74c19be522b41572e436f4bbfac301809f5fed3de2abd6bf0eb522eff4a08608057a072" style="font-family:Arial,sans-serif;color:#000000;text-align:center;text-decoration:none;display:block;font-size:16px;padding:8px 25px 9px;border-radius:30px" width="130" target="_blank">sprawdź</a>
            </td></tr><tr><td height="4"></td></tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>
            </tbody></table>
            <table style="display:inline-block;float:left" class="m_2707632969868879294section-promo" width="40%" cellspacing="0" cellpadding="0" align="left">
            <tbody><tr>
            <td align="left">
            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="left">
            <tbody><tr>
            <td class="m_2707632969868879294section-photo-left" align="left">
            <a title="poznaj nasze aktualne okazje" href="https://click.mail.x-kom.pl/?qs=9603a7c2195459f729f3ab24d22948b09451793f50ba0ded892455c91dc3201892af167e7c61db5cd18b36a1d20ba7798488e35550f0b2c6896a62512867a898" target="_blank"><img src="https://image.mail.x-kom.pl/lib/fe2e117170640474751079/m/1/dac3ead0-cad1-4a71-9373-dd0be750f154.jpg" width="100%"></a>
            </td>
            </tr>
            </tbody></table>
            </td>
            </tr>\s
                       
            <img src="https://click.mail.x-kom.pl/open.aspx?ffcb10-fe9315777264057b73-fe2f157073660c7d761276-fe2e117170640474751079-ff5f137777-fe221175776606747d1c74-ff091671756506&amp;d=100191&amp;bmt=0" width="1" height="1" alt="">
            </div></div></div>
            """;

    static final String XKOM_NEWSLETTER_PLAIN = """
            ---------- Forwarded message ---------
            From: x-kom <mailing@mail.x-kom.pl>
            Date: Mon, 21 Aug 2023 at 07:05
            Subject: 💻 Laptopy do 1400 zł taniej
            To: <senderEmail@gmail.com>
                       
                       
            Nie tylko do nauki >>
             ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌
             ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌
             ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌
             ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌
             ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌
             ‌ ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌
             ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌   ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌
            ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌  ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌ ‌  ‌ ‌
            zobacz w przeglądarce »
            <https://view.mail.x-kom.pl/?qs=10260dfaaa7d1119e4f3a47a58669177963276cbc7c7ef0f0cd0fab6c84b2aafd7042a9eddb3380e283d3a431c18f23777bf7a0d550603fa4ba75281572d044b882e8a82e285bfba376891355305fa1b7d203df7e9a2eed8>
            <https://click.mail.x-kom.pl/?qs=a272951347045344471de7f25f87f1e9169ed379a8a684b3473a85067d11fa50e7cc57f23c3b548843d6e96788abcdb69753e90824b08a0228e64370fd41df32>
            oczywiście, że do nauki
            * laptopy taniej nawet o 1400 zł *
            użyj kodu: bts-laptopy
            skorzystaj
            <https://click.mail.x-kom.pl/?qs=a27295134704534428d58d85f4acb5c983d13b6d0d4232edb5459ba75473378fb24d2e66231b6d2b1406e81c931a6d2eddd27a5e13a284fc0336954fd069629a>
            [image: top-1-1]
            <https://click.mail.x-kom.pl/?qs=a272951347045344535fdaabedd1826e91ca9b60ae7469ef50af3625d0f0bc133428850b5af7e2a85114f04ede3c50d744b75cae2877290d641492ca37055b62>
            <https://click.mail.x-kom.pl/?qs=a2729513470453445f8876efa3b37bf3e28e863cf9adcad4116df997d5c5679072eecf336b4c561b8c751f6e57e9a3c067c8ccc537d104cdd7e14dd3895f999f>
            przygotuj się na powrót do szkoły
            <https://click.mail.x-kom.pl/?qs=a272951347045344d3145952cdea04f35a66b8ed0bef840f159fce0be7da2bc6a6ef8b77f8c744b32fb6b1538d45d6dc6d9a541a6ed1870fbb2dcdd38b8433f0>
            laptop 14”
            <https://click.mail.x-kom.pl/?qs=a2729513470453446ae479459f0861bcec8bdf53200329ed5e50f67426492046495c0e99c0087572fecc78f137392fd040499991f35b4b55b0085daad0afd9a7>
            ASUS ZenBook 14 UM425QA R5-5600H/16GB/ 512/Win11
            <https://click.mail.x-kom.pl/?qs=a2729513470453449253288f65dd4cfca0a090e2c7d8fffa35093055b162173fcb512cdbad44ec26dc3d7f46db31cb87b89580432250c46f77ece30c574bca7a>
            3999 zł    3599 zł
            <https://click.mail.x-kom.pl/?qs=a272951347045344281b6928c99cbf7b972324770a64ca948672346c79ddc168bff83e38ada20f0e97444db83b4356f60ef4fe578f76b348ca2635a792dfcf69>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a2729513470453445015ceb351faa15f7b8c5fad94f9669e6f4c275201408d23cc985fe3cf38fe83250497f905aa2e1f10fe9814794e48feae9d2fa94916e029>
            laptop 16”
            <https://click.mail.x-kom.pl/?qs=a272951347045344cac5ec5619ef115c3ebd407d6c112ce2f29a698908c24483ebd61b06659c46ea8328317962b25895cceca755e35276821835f3510887f69e>
            Dell Inspiron 5625 Ryzen 5 5625U/16GB/ 512/Win11
            <https://click.mail.x-kom.pl/?qs=a272951347045344e97d53f2064660ab7bd19932efe09c4ca55d5f1ee9781b72950473505a2b002ceedfa2fd724894ed873da1d406dab2081f447fe915f8922d>
            3999 zł    2999 zł
            <https://click.mail.x-kom.pl/?qs=a27295134704534447183dc04f62a16c903424abd105c0c7cb065356e1c0d43c7b6bd2d05e04f3d44418bb9d4935532fa515e782b3e55379c5fa7b47342e7044>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a272951347045344234c2ca203fa6c18cb6b688ca1c24e46869670164d1f0f05d0160e7ec8a591b12d3eee74b7e71910d5e333f71a468156ac44b192fc90522d>
            laptop 17,3”
            <https://click.mail.x-kom.pl/?qs=a272951347045344a355ccc076347a973eefd9d8b33e470cbf33ab2c9bf1df82f25d9eaead3cd798841472836ce613cae9c1c48c6e51889ed25632accda3f774>
            MSI GF76 i7-12650H/8GB/ 512 RTX3050 144Hz
            <https://click.mail.x-kom.pl/?qs=a27295134704534485fff67839f116dc5d335c5e105995f0f951b6dbec1f441e7135c36c093710b024d30fa3ffb192c526237f7896f5b9582562df18a09ae46f>
            3999 zł    3599 zł
            <https://click.mail.x-kom.pl/?qs=a272951347045344eaf1fa566b9e6ab79f9c161202479f84897059f8358c80fd07c3690228e941d268eaf576ba3f1124b190cd705740d14a33874848c27791b3>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a272951347045344c4198d00f628de1e479bec6e24d408b60e2637127adc6f35f75d4f414c92a56534885a57add4a71ca570f42ad8a059f1dccb4d625a085002>
            mysz
            <https://click.mail.x-kom.pl/?qs=a2729513470453447b76dd32f22447c0ceb5e443934aa1628b3726fcd0e7522b9441fd754106d3cce9648fead7f650c12804ff1406f0ce4f77134f8988187919>
            Razer Naga V2 Pro
            <https://click.mail.x-kom.pl/?qs=a27295134704534475943e06be6fa05e2e1a613f23444420a311c4a67ba174649865931d678e623ffef1cc41ceb64fcb55031af254754655f766fe140becbd2a>
            899 zł    666 zł
            <https://click.mail.x-kom.pl/?qs=a272951347045344a2f71a4f78e921fc4e8446c5c1dd81c081d5c69ad1d3988027cf9d3ef1dfc8134aa5799f85d9613ba6db2865d2d7ee82e3b65a69b566ee53>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a2729513470453447c496355ee0204008025701d5c5978f2f926d6fd93d14a077f69b1ee302f752abe7f14efc97c64d1322eb8bc79b31ebb30b700949ff13e77>
            podstawka
            <https://click.mail.x-kom.pl/?qs=a272951347045344a2886e77263ab231fa5b4ff6dbdb692f7bac635a31447411f749ee1d6ef94729be71cc907067df70afd93e5b797b52b36ddab310dafa750f>
            Mozos LS3-ALU aluminiowa
            <https://click.mail.x-kom.pl/?qs=a272951347045344b76e0d638a6b234cc3705a42357c8da7c780c88e2e90c63ba7c0b8d236e72ff4fa6f885d0fcd672a789dc7d59717a1503a8b829e96da1a54>
            139 zł    95 zł
            <https://click.mail.x-kom.pl/?qs=a2729513470453440a87caaa0d9e0fe9c7f688bef3fe8f6285c9c6655b739ceac442a77dc68db2d8f5eee21aae2a6490fb35056552e2b83748b8d2f988caae6b>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a272951347045344831846f7e20b7693913cdf0cd40321c15dda03abd9402a9a1434c41acd88247b8914eda385a14e3be0ad98479c875e13ed96948cfb52e61c>
            antywirus
            <https://click.mail.x-kom.pl/?qs=a272951347045344ce909fa5985513bc171212c3d04e8b3955c3bec883ff271d70007ae4146f21f5e7865f1f49743981b075e5c26f0c9f985db0d520588593b6>
            NortonLifeLock Antivirus Plus 1st. (12m.)
            <https://click.mail.x-kom.pl/?qs=a272951347045344ed517942112324743e29b24f96bde723ef6c2c8aa754ca87be6c6940988f3c8594fc334b614458ddc0aadbf25526dcbfc554dd6fffcd475a>
            69 zł    19 zł
            <https://click.mail.x-kom.pl/?qs=a27295134704534462dfbfda20556896bfdedf0eab32ebf2bdf264ba26658cc06dd00bdc22a63ce1702d7baab985bc9843ddef81360f8850db475227e755ac4b>
                       
                       
            <https://click.mail.x-kom.pl/?qs=a2729513470453446fd72821d30f3529d3c66f800ad2dd98ff71fda94bb4e6e76447f301ab1b09768057f302263979e511455768835e3e704e7bcf73b5ef79a8>
            laptop 17,3”
            <https://click.mail.x-kom.pl/?qs=a272951347045344a355ccc076347a973eefd9d8b33e470cbf33ab2c9bf1df82f25d9eaead3cd798841472836ce613cae9c1c48c6e51889ed25632accda3f774>
            MSI GF76 i7-12650H/8GB/512 RTX3050 144Hz
            <https://click.mail.x-kom.pl/?qs=a2729513470453442e9b6e903d2ee79cd7e8849099970c916dfccdafd1459b5db9093320ae02a44a85259cc8791b2c31aca27421de2c2cd2391c84af1952248c>
            3999 zł    3599 zł
            <https://click.mail.x-kom.pl/?qs=a272951347045344eaf1fa566b9e6ab79f9c161202479f84897059f8358c80fd07c3690228e941d268eaf576ba3f1124b190cd705740d14a33874848c27791b3>
            <https://click.mail.x-kom.pl/?qs=a272951347045344d4054be9c48ca3123491301f8424ac2c1b7593771eb97c68f84bb6f79e6577121105311d61ddd8f2d27982e534e2fe281de5dbf1bfb3a65b>
            antywirus
            <https://click.mail.x-kom.pl/?qs=a272951347045344ce909fa5985513bc171212c3d04e8b3955c3bec883ff271d70007ae4146f21f5e7865f1f49743981b075e5c26f0c9f985db0d520588593b6>
            NortonLifeLock Antivirus Plus 1st. (12m.)
            <https://click.mail.x-kom.pl/?qs=a2729513470453443ae4b5a07d4d681ba73baafee88744c61f6f582ff792aa43eaa646d092270e0ec6a47c0df2fa15f54a3d5ab62c5d7012c709baf7020571d9>
            69 zł    19 zł
            <https://click.mail.x-kom.pl/?qs=a27295134704534462dfbfda20556896bfdedf0eab32ebf2bdf264ba26658cc06dd00bdc22a63ce1702d7baab985bc9843ddef81360f8850db475227e755ac4b>
            zobacz więcej
            <https://click.mail.x-kom.pl/?qs=a272951347045344abb2f3baa9e0cc7e4cfa064c6e6d0a4aa9cbc8e7eb48209793447fdf36fcb6f340924d9161cc5c7ee5264316b7d602f4b5747a7c98d02d7d>
            [image: darmowa dostawa] darmowa dostawa
            w aplikacji od 99 zł
            [image: zwroty paczkomat] bezpłatne zwroty
            w Paczkomacie
            [image: zwroty paczkomat] profesjonalne
            doradztwo ekspertów
            odkryj nasze aktualne okazje
                       
            kup Galaxy Z Fold5 lub Z Flip5
            i zyskaj do 3000 zł
            oraz Galaxy Buds2 Pro
            sprawdź
            <https://click.mail.x-kom.pl/?qs=a272951347045344a92e01d880db7109ed0d4897e7ce34754ec9cd8f5cff851b9a041cc8c4aacfa831481eb50020f5e20196c8cc2be37caabc64f25cf0ee5387>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7cd2264341e46dfd2aa7fd1afb9c7bd93ace0a9c60d249a4bc2698d94e15e79d6cb9d4d1a1cba37f4f4f408c1f76ffcf3449f2f61ec4817f5>
                       
                       
            laptopy Chromebook
            już od 899 zł
            sprawdź
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7f268286731ad9e0598ad2f12eca06fff0d406659a74c19be522b41572e436f4bbfac301809f5fed3de2abd6bf0eb522eff4a08608057a072>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f729f3ab24d22948b09451793f50ba0ded892455c91dc3201892af167e7c61db5cd18b36a1d20ba7798488e35550f0b2c6896a62512867a898>
            poznaj bliżej grupę x-kom
            i znajdź coś dla siebie
            [image: al.to]
            nie przegap okazji
            LEGO® z rabatami
            sprawdź
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7e1714d234fca2836ed30d0b637d38c71e5db7bd1d1da62b397d7beb8513f7389ce565717bdd9dc5a9a4db72ddb0fee3d4098e9502f1fbf46>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7ca04507812662822d89b6bb9e40a1034f48a0e80f4b5a3930de4d00cb5cb769fca9237bf0e727f39986be420f9030c6c8a99525ee1ca6757>
            odwiedź naszą nową promocję
                       
            codziennie otwieraj 3 boxy
                       
            *i zgarnij sprzętnawet za 1 zł*
            odkryj un.Box
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f771a9c31434a4315b922c4691b2f69765d6afd2d0d7ad3439a4bd77870505100aeaa7e2fd55b0b8c1a8c8a1bce4efac4e6eaa3459c44e424d>
            codziennie otwieraj 3 boxy
            *i zgarnij sprzęt nawet za 1 zł *
            odkryj un.Box
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f771a9c31434a4315b922c4691b2f69765d6afd2d0d7ad3439a4bd77870505100aeaa7e2fd55b0b8c1a8c8a1bce4efac4e6eaa3459c44e424d>
            www.x-kom.pl
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f76bc1813a8619e3a71e62c8f03d985f81cfc6506382048f28acc390631a81caba49862a55bfdda478537320fe7db54f0d6540690e17de2c23>
            +48 34 377 00 00 <+48343770000>
            x-kom@x-kom.pl
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7747552f2e68259d42231682d0ab1fea09dc88c7905669538579a891bc2b8e880b4486299263b672ae443ba4ff40204095ec5213d43deb361>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7079ecbcecc2a965704997c76a3e3273308b1ba63a1e3092c7ef82d67959aa429c26e24c6e494664c4d21572a8ca7026e65d48082248fb835>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7b0a43034699ef45d57bd68ad0b798aea9fa1b70c47c690bb5ed9b1ba0b5312ea19d9fbbb05003024a6e34f24eee24ac0a47f74b86cd37780>
            <https://click.mail.x-kom.pl/?qs=9603a7c2195459f7cf4a670f0668cd31fe58be3b092b26fb427c40191df79b9336221472246759c3c2b5da4ef7e7a58adc5ac34920f722e9b70f5d472f4bd276>
                       
            """;


}

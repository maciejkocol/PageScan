Feature: Scan


  @scan @scan_01
  Scenario Outline: Scan page for problems
    Given A user navigates to page "<url>"
    Then There are no console errors
    And The response code is OK
    And All links work

    Examples:
      | url |
      | https://www.w3.org/standards/badpage |
      | https://www.w3.org/standards/webofdevices/multimodal |
      | https://www.w3.org/standards/webdesign/htmlcss       |


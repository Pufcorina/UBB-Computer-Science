<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
    <head>
        <link type="text/css" rel="stylesheet" href="lab4.css"/>
        <link href="bootstrap.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="problem">
            List by using XSLT the content of an XML document so that: 
                - each sub-element within the same element has to have a different formatting; 
                - the first XML element has a different formatting from the other elements. 
            The XSLT file should also use at least 6 Bootstrap CSS classes for formatting XML elements.
        </div>
        <h2>My Playlist Collection</h2>
        <table border="1">
            <tr>
            <th>Title</th>
            <th>Artist</th>
            <th>Year</th>
            <th>Genre</th>
            </tr>
            <xsl:for-each select="//record[1]">
                <tr class="first">
                    <td><xsl:value-of select="title"/></td>
                    <td><xsl:value-of select="artist"/></td>
                    <td><xsl:value-of select="year"/></td>
                    <td><xsl:value-of select="genre"/></td>
                </tr>
            </xsl:for-each>
            <xsl:for-each select="playlist/record[position()>1]">
                <tr>
                    <td class="title"><xsl:value-of select="title"/></td>
                    <td class="artist"><xsl:value-of select="artist"/></td>
                    <td class="year"><xsl:value-of select="year"/></td>
                    <td class="genre"><xsl:value-of select="genre"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
  </html>
</xsl:template>
</xsl:stylesheet>

<!--
<h2>My Playlist Collection</h2>
        <table border="1">
            <tr bgcolor="#9acd32">
            <th>Title</th>
            <th>Artist</th>
            <th>Year</th>
            <th>Genre</th>
            </tr>
            <xsl:for-each select="//record[1]">
                <tr class="first">
                    <td><xsl:value-of select="title"/></td>
                    <td><xsl:value-of select="artist"/></td>
                    <td><xsl:value-of select="year"/></td>
                    <td><xsl:value-of select="genre"/></td>
                </tr>
            </xsl:for-each>
            <xsl:for-each select="playlist/record[position()>1]">
                <tr>
                    <td class="title"><xsl:value-of select="title"/></td>
                    <td class="artist"><xsl:value-of select="artist"/></td>
                    <td class="year"><xsl:value-of select="year"/></td>
                    <td class="genre"><xsl:value-of select="genre"/></td>
                </tr>
            </xsl:for-each>
-->